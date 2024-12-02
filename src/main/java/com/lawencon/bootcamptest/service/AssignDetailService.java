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
import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseUpdateResDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqDeleteDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqInsertDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqUpdateDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailResDataDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageResDataDto;
import com.lawencon.bootcamptest.model.Assign;
import com.lawencon.bootcamptest.model.AssignDetail;
import com.lawencon.bootcamptest.model.doc.QuestionPackage;

@Service
public class AssignDetailService {
    
    private AssignDao assignDao;
    private AssignDetailDao assignDetailDao;
    private QuestionPackageDao questionPackageDao;

    public AssignDetailService(AssignDetailDao assignDetailDao,AssignDao assignDao,QuestionPackageDao questionPackageDao){
        this.assignDetailDao = assignDetailDao;
        this.assignDao = assignDao;
        this.questionPackageDao = questionPackageDao;
    }

    public BaseListResDto getAll(String assignId,Integer page,Integer limit,Boolean ascending,
    Boolean isActive){
        BaseListResDto baseListResDto = new BaseListResDto();

        Map<OptionalBuilder,Object> options = new HashMap<>();

        Map<String,Object> wheres = new HashMap<>();
        Map<String,Object> joins = new HashMap<>();

        joins.put("tb_assign", "id");

        options.put(OptionalBuilder.JOIN, joins);
        options.put(OptionalBuilder.ID, "assign_id");

        wheres.put("tb_assign_detail.is_active", isActive);

		options.put(OptionalBuilder.WHERE, wheres);
		options.put(OptionalBuilder.RELATION, "AND");

		Integer countOfAssignDetail =  assignDetailDao.getCountOfAllBy("tb_assign", options);
		
		options.put(OptionalBuilder.LIMIT,limit);
		options.put(OptionalBuilder.PAGE,page);


        List<AssignDetailResDataDto> assignDetailResDataDtos = new ArrayList<>();

        List<AssignDetail> assignDetails = assignDetailDao.getAllBy("tb_assign_detail", AssignDetail.class, options);

        // assign data
        for (int i = 0; i < assignDetails.size(); i++) {
            AssignDetail assignDetail = assignDetails.get(i);

            QuestionPackage questionPackage = assignDetail.getQuestionPackage();
            AssignDetailResDataDto assignDetailResDataDto = new AssignDetailResDataDto();

            assignDetailResDataDto.setId(assignDetail.getId());
            assignDetailResDataDto.setScore(assignDetail.getScore());
            assignDetailResDataDto.setStatus(assignDetail.getStatus());
            assignDetailResDataDto.setNote(assignDetail.getNote());

            QuestionPackageResDataDto packageResDataDto = new QuestionPackageResDataDto();
            packageResDataDto.setId(questionPackage.getId());
            packageResDataDto.setQuestionPackageCode(questionPackage.getQuestionPackageCode());
            packageResDataDto.setQuestionPackageName(questionPackage.getQuestionPackageName());

            assignDetailResDataDto.setQuestionPackage(packageResDataDto);

            assignDetailResDataDtos.add(assignDetailResDataDto);
        }

        baseListResDto.setData(assignDetailResDataDtos);
        baseListResDto.setModel(AssignDetail.class);
        baseListResDto.setPage(page);
        baseListResDto.setLimit(limit);
        baseListResDto.setTotal(countOfAssignDetail);

        return baseListResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(AssignDetailReqInsertDto assignDetailReqInsertDto){
        BaseInsertResDto insertResDto = new BaseInsertResDto();

        QuestionPackage questionPackage = questionPackageDao.getByIdAndDetach(QuestionPackage.class, assignDetailReqInsertDto.getQuestionPackageId());
        Optional<QuestionPackage> optQuestionPackage = Optional.ofNullable(questionPackage);

        Assign assign = assignDao.getByIdAndDetach(Assign.class, assignDetailReqInsertDto.getAssignId());
        Optional<Assign> optAssign = Optional.ofNullable(assign);

        if(!optAssign.isEmpty() && !optQuestionPackage.isEmpty()){
            AssignDetail assignDetail = new AssignDetail();
            assignDetail.setAssign(assign);
            assignDetail.setQuestionPackage(questionPackage);

            assignDetail.setScore(0f);
            assignDetail.setStatus(assignDetailReqInsertDto.getStatus());
            assignDetail.setNote(assignDetailReqInsertDto.getNote());
            assignDetail.setCreatedBy("1");


            AssignDetail assignDetailInsert = assignDetailDao.save(assignDetail);

            if(assignDetailInsert != null){
                insertResDto.setId(assignDetailInsert.getId());
                insertResDto.setMessage("Data saved");
            }else{
                insertResDto.setMessage("Data failed to save");
            }
        }else{
            insertResDto.setMessage("Question package not available");
        }

        return insertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateResDto update(AssignDetailReqUpdateDto assignDetailReqUpdateDto){
        BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

        AssignDetail assignDetail = assignDetailDao.getByIdAndDetach(AssignDetail.class,assignDetailReqUpdateDto.getId());
        Optional<AssignDetail> optAssign = Optional.ofNullable(assignDetail);

        if(optAssign.isPresent()){

            if(assignDetail.getVersion() == assignDetailReqUpdateDto.getVersion()){

                if(assignDetailReqUpdateDto.getStatus() != null){
                    if(!assignDetailReqUpdateDto.getStatus().equals(assignDetail.getStatus())){
                        assignDetail.setStatus(assignDetailReqUpdateDto.getStatus());
                    }
                }
    
                if(assignDetailReqUpdateDto.getNote() != null){
                    if(!assignDetailReqUpdateDto.getNote().equals(assignDetail.getNote())){
                        assignDetail.setNote(assignDetailReqUpdateDto.getNote());
                    }
                }

                if(assignDetailReqUpdateDto.getScore() != null){
                    if(!assignDetailReqUpdateDto.getScore().equals(assignDetail.getScore())){
                        assignDetail.setScore(assignDetailReqUpdateDto.getScore());
                    }
                }
    
                assignDetail.setUpdatedBy("1");
    
                AssignDetail assignUpdate =  assignDetailDao.update(assignDetail);
    
                if(assignUpdate != null){
                    baseUpdateResDto.setVersion(assignUpdate.getVersion());
                    baseUpdateResDto.setMessage("Data updated");
                }
            }else{
                baseUpdateResDto.setMessage("version isnt same");
            }
        }else{
            baseUpdateResDto.setMessage("assign detail not available");
        }

        return baseUpdateResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseDeleteResDto hardDelete(AssignDetailReqDeleteDto assignDetailReqDeleteDto){
        BaseDeleteResDto dto = new BaseDeleteResDto();

        Map<OptionalBuilder,Object> options = new HashMap<>();
        Map<String,Object> wheres = new HashMap<>();

        wheres.put("id", assignDetailReqDeleteDto.getId());

        options.put(OptionalBuilder.WHERE, wheres);
        options.put(OptionalBuilder.RELATION, "AND");

        Boolean deleteAssign = assignDetailDao.removeBy("tb_assign_detail", Assign.class, options);

        if(deleteAssign){
            dto.setMessage("Data deleted");
        }else{
            dto.setMessage("assign detail not available");
        }

        return dto;
    }
}
