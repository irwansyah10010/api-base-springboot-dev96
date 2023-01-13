package com.lawencon.bootcamptest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.bootcamptest.constant.OptionalBuilder;
import com.lawencon.bootcamptest.dao.AssignDao;
import com.lawencon.bootcamptest.dao.AssignDetailDao;
import com.lawencon.bootcamptest.dao.QuestionPackageDao;
import com.lawencon.bootcamptest.dao.UserDao;
import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.assign.AssignReqDeleteDto;
import com.lawencon.bootcamptest.dto.assign.AssignReqInsertDto;
import com.lawencon.bootcamptest.dto.assign.AssignResDataDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailResDataDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageResDataDto;
import com.lawencon.bootcamptest.dto.user.UserResDataDto;
import com.lawencon.bootcamptest.model.Assign;
import com.lawencon.bootcamptest.model.AssignDetail;
import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.QuestionPackage;
import com.lawencon.bootcamptest.model.User;

@Service
public class AssignService {
    
    private UserDao userDao;
    private AssignDao assignDao;
    private AssignDetailDao assignDetailDao;
    private QuestionPackageDao questionPackageDao;

    public AssignService(UserDao userDao,AssignDao assignDao,AssignDetailDao assignDetailDao,QuestionPackageDao questionPackageDao){
        this.userDao = userDao;
        this.assignDao = assignDao;
        this.assignDetailDao = assignDetailDao;
        this.questionPackageDao = questionPackageDao;
    }

    public BaseListResDto getAll(Integer page,Integer limit,Boolean ascending,
    Boolean isActive){
        BaseListResDto baseListResDto = new BaseListResDto();

        Map<OptionalBuilder,Object> options = new HashMap<>();

        Map<String,Object> wheres = new HashMap<>();
        Map<String,Object> joins = new HashMap<>();

        joins.put("tb_user", "id");

        options.put(OptionalBuilder.JOIN, joins);
        options.put(OptionalBuilder.ID, "user_candidate_id");

        wheres.put("tb_assign.is_active", isActive);

		options.put(OptionalBuilder.WHERE, wheres);
		options.put(OptionalBuilder.RELATION, "AND");

		Integer countOfQuestionBank =  assignDao.getCountOfAllBy("tb_assign", options);
		
		options.put(OptionalBuilder.LIMIT,limit);
		options.put(OptionalBuilder.PAGE,page);
        
        List<Assign> assigns = assignDao.getAllBy("tb_assign", Assign.class, options);

        List<AssignResDataDto> assignResDataDtos = new ArrayList<>();

        for (int i = 0; i < assigns.size(); i++) {
            Assign assign = assigns.get(i);
            User candidate = assign.getUserCandidate();
            File cvCandidate = candidate.getCv();
            File profileCandidate = candidate.getFile();

            User reviewer = userDao.getById(User.class, assign.getUserReviewer().getId());

            AssignResDataDto assignResDataDto = new AssignResDataDto();
            assignResDataDto.setId(assign.getId());

            UserResDataDto candidateResDataDto = new UserResDataDto();
            candidateResDataDto.setId(candidate.getId());
            candidateResDataDto.setFullName(candidate.getFullName());
            candidateResDataDto.setEmail(candidate.getEmail());
            candidateResDataDto.setPlaceOfBirth(candidate.getPlaceOfBirth());
            candidateResDataDto.setDateOfBirth(candidate.getDateOfBirth());
            candidateResDataDto.setAddress(candidate.getAddress());
            candidateResDataDto.setCv(cvCandidate);
            candidateResDataDto.setFile(profileCandidate);

            UserResDataDto reviewerResDataDto = new UserResDataDto();
            reviewerResDataDto.setId(reviewer.getId());
            reviewerResDataDto.setFullName(reviewer.getFullName());
            reviewerResDataDto.setEmail(reviewer.getEmail());
            reviewerResDataDto.setPlaceOfBirth(reviewer.getPlaceOfBirth());
            reviewerResDataDto.setDateOfBirth(reviewer.getDateOfBirth());
            reviewerResDataDto.setAddress(reviewer.getAddress());

            options = new HashMap<>();
            wheres = new HashMap<>();
            joins = new HashMap<>();

            wheres.put("assign_id", assign.getId());

            options.put(OptionalBuilder.WHERE, wheres);
            options.put(OptionalBuilder.RELATION, "AND");

            joins.put("tb_question_package","id");

            options.put(OptionalBuilder.JOIN, joins);
            options.put(OptionalBuilder.ID, "question_package_id");

            List<AssignDetailResDataDto> assignDetailResDataDtos = new ArrayList<>();
            List<AssignDetail> assignDetails = assignDetailDao.getAllBy("tb_assign_detail", AssignDetail.class , options);

            // assign detail
            for (int j = 0; j < assignDetails.size(); j++) {
                AssignDetail assignDetail = assignDetails.get(i);

                QuestionPackage questionPackage = assignDetail.getQuestionPackage();
                QuestionPackageResDataDto questionPackageResDataDto = new QuestionPackageResDataDto();

                questionPackageResDataDto.setId(questionPackage.getId());
                questionPackageResDataDto.setQuestionPackageCode(questionPackage.getQuestionPackageCode());
                questionPackageResDataDto.setQuestionPackageName(questionPackage.getQuestionPackageName());


                AssignDetailResDataDto assignDetailResDataDto = new AssignDetailResDataDto();
                assignDetailResDataDto.setId(assignDetail.getId());
                assignDetailResDataDto.setQuestionPackage(questionPackageResDataDto);

                assignDetailResDataDtos.add(assignDetailResDataDto);
            }

            assignResDataDto.setListAssignDetail(assignDetailResDataDtos);
            assignResDataDto.setUserCandidate(candidateResDataDto);
            assignResDataDto.setUserReviewer(reviewerResDataDto);
            
            assignResDataDtos.add(assignResDataDto);
        }

        baseListResDto.setData(assignResDataDtos);
        baseListResDto.setModel(Assign.class);
        baseListResDto.setTotal(countOfQuestionBank);
        baseListResDto.setPage(page);
        baseListResDto.setLimit(limit);

        return baseListResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(AssignReqInsertDto assignReqInsertDto){
        BaseInsertResDto insertResDto = new BaseInsertResDto();

        User userCandidate = assignDao.getByIdAndDetach(User.class, assignReqInsertDto.getUserCandidateId());
        Optional<User> optCandidate = Optional.ofNullable(userCandidate);

        User userReviewer = assignDao.getByIdAndDetach(User.class, assignReqInsertDto.getUserReviewerId());
        Optional<User> optReviewer = Optional.ofNullable(userReviewer);

        if(optCandidate.isPresent() && optReviewer.isPresent()){
            Assign assign = new Assign();
            assign.setUserCandidate(userCandidate);
            assign.setUserReviewer(userReviewer);
            assign.setCreatedBy("1");

            Assign assignInsert = assignDao.save(assign);

            if(assignInsert != null){
                QuestionPackage questionPackage = questionPackageDao.getById(QuestionPackage.class, assignReqInsertDto.getQuestionPackageId());
                Optional<QuestionPackage> optQuestionPackage = Optional.ofNullable(questionPackage);

                if(optQuestionPackage.isPresent()){
                    AssignDetail assignDetail = new AssignDetail();
                    
                    assignDetail.setAssign(assignInsert);
                    assignDetail.setQuestionPackage(questionPackage);

                    assignDetail.setNote("");
                    assignDetail.setScore(0f);

                    assignDetail.setCreatedBy("1");

                    AssignDetail assignDetailInsert = assignDetailDao.save(assignDetail);
                    
                    if(assignDetailInsert != null){
                        insertResDto.setId(assignDetailInsert.getId());
                        insertResDto.setMessage("Data saved and question package included");
                    }else{
                        insertResDto.setMessage("Data failed to save");
                    }
                }else{
                    insertResDto.setId(assignInsert.getId());
                    insertResDto.setMessage("Data saved");
                }
            }else{
                insertResDto.setMessage("Data assign failed to save");
            }
        }else{
            insertResDto.setMessage("User not available");
        }

        return insertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseDeleteResDto hardDelete(AssignReqDeleteDto deleteDto){
        BaseDeleteResDto dto = new BaseDeleteResDto();

        Map<OptionalBuilder,Object> options = new HashMap<>();
        Map<String,Object> wheres = new HashMap<>();

        wheres.put("assign_id", deleteDto.getId());

        options.put(OptionalBuilder.WHERE, wheres);
        options.put(OptionalBuilder.RELATION, "AND");

        List<AssignDetail> assignDetails = assignDetailDao.getAllBy("tb_assign_detail", AssignDetail.class , options);

        if(!assignDetails.isEmpty()){
            options = new HashMap<>();
            wheres = new HashMap<>();
    
            wheres.put("assign_id", deleteDto.getId());
    
            options.put(OptionalBuilder.WHERE, wheres);
            options.put(OptionalBuilder.RELATION, "AND");
    
            Boolean deleteAssignDetail = assignDetailDao.removeBy("tb_assign_detail", AssignDetail.class, options);
    
            if(deleteAssignDetail){
                options = new HashMap<>();
                wheres = new HashMap<>();
    
                wheres.put("id", deleteDto.getId());
    
                options.put(OptionalBuilder.WHERE, wheres);
                options.put(OptionalBuilder.RELATION, "AND");
    
                Boolean deleteAssign = assignDao.removeBy("tb_assign", Assign.class, options);
    
    
                if(deleteAssign){
                    dto.setMessage("Data deleted");
                }
            }
        }else{
            options = new HashMap<>();
            wheres = new HashMap<>();

            wheres.put("id", deleteDto.getId());

            options.put(OptionalBuilder.WHERE, wheres);
            options.put(OptionalBuilder.RELATION, "AND");

            Boolean deleteAssign = assignDao.removeBy("tb_assign", Assign.class, options);

            if(deleteAssign){
                dto.setMessage("Data deleted");
            }
        }


        return dto;
    }
}
