package com.lawencon.bootcamptest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.bootcamptest.constant.OptionalBuilder;
import com.lawencon.bootcamptest.dao.QuestionBankDao;
import com.lawencon.bootcamptest.dao.QuestionMultipleChoiceDao;
import com.lawencon.bootcamptest.dao.QuestionPackageDao;
import com.lawencon.bootcamptest.dao.QuestionPackageDetailDao;
import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseUpdateResDto;
import com.lawencon.bootcamptest.dto.questionbank.QuestionBankResDataDto;
import com.lawencon.bootcamptest.dto.questionmultiplechoice.QuestionMultipleChoiceResDataDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageDeleteReqDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageInsertReqDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageResDataDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageUpdateReqDto;
import com.lawencon.bootcamptest.dto.questionpackagedetail.QuestionPackageDetailResDataDto;
import com.lawencon.bootcamptest.model.QuestionBank;
import com.lawencon.bootcamptest.model.QuestionMultipleChoice;
import com.lawencon.bootcamptest.model.QuestionPackage;
import com.lawencon.bootcamptest.model.QuestionPackageDetail;
import com.lawencon.bootcamptest.model.QuestionType;

@Service
public class QuestionPackageService {
    
    private QuestionPackageDao questionPackageDao;
    private QuestionPackageDetailDao questionPackageDetailDao;
    private QuestionMultipleChoiceDao questionMultipleChoiceDao;
    private QuestionBankDao questionBankDao;

    public QuestionPackageService(QuestionPackageDao questionPackageDao,QuestionPackageDetailDao questionPackageDetailDao,QuestionMultipleChoiceDao questionMultipleChoiceDao,
            QuestionBankDao questionBankDao){
        this.questionPackageDao = questionPackageDao;
        this.questionPackageDetailDao = questionPackageDetailDao;
        this.questionMultipleChoiceDao = questionMultipleChoiceDao;
        this.questionBankDao = questionBankDao;
    }

    public BaseListResDto getAll(String questionTypeCode,Integer startPosition,Integer limit,Boolean isActive){
        // set all and count question package
        Map<OptionalBuilder,Object> optPck = new HashMap<>();

        Map<String,Object> wheresPck = new HashMap<>();
        wheresPck.put("is_active", isActive);

        optPck.put(OptionalBuilder.WHERE, wheresPck);
        optPck.put(OptionalBuilder.RELATION, "AND");

        optPck.put(OptionalBuilder.START, startPosition);
        optPck.put(OptionalBuilder.LIMIT, limit);

        List<QuestionPackage> questionPackages = questionPackageDao.getAllBy("tb_question_package", QuestionPackage.class, optPck);
        Integer countOfquestionPackages = questionPackageDao.getCountOfAllBy("tb_question_package", optPck);

        List<QuestionPackageResDataDto> questionPackageResDataDtos = new ArrayList<>();
        BaseListResDto baseListDto = new BaseListResDto();

        for(int i = 0;i < questionPackages.size();i++){
            QuestionPackage questionPackage = questionPackages.get(i);

            Map<OptionalBuilder,Object> opt = new HashMap<>();
            Map<String,Object> wheres = new HashMap<>();
            Map<String,Object> joins = new HashMap<>();

            wheres.put("question_package_id", questionPackage.getId());
            
            if(questionTypeCode != null){
                wheres.put("question_type_code", questionTypeCode);
            }

            opt.put(OptionalBuilder.WHERE, wheres);
            opt.put(OptionalBuilder.RELATION, "AND");

            joins.put("tb_question_bank", "id");
            

            Map<OptionalBuilder,Object> customTable = new HashMap<>();
            customTable.put(OptionalBuilder.ID_FK, "id");
            customTable.put(OptionalBuilder.ID_OTHER, "question_type_id");
            customTable.put(OptionalBuilder.TABLE_OTHER, "tb_question_bank");

            joins.put("tb_question_type",customTable);

            opt.put(OptionalBuilder.JOIN, joins);
            opt.put(OptionalBuilder.ID,"question_bank_id");

            // get question package detail and count base on package id
            List<QuestionPackageDetail> questionPackagesDetails = questionPackageDetailDao.getAllBy("tb_question_package_detail", 
                        QuestionPackageDetail.class,opt);
            Integer countOfquestionPackagesDetail = questionPackageDetailDao.getCountOfAllBy("tb_question_package_detail", opt);

            List<QuestionPackageDetailResDataDto> questionPackageDetailResDataDtos = new ArrayList<>();

            for(int j = 0;j < questionPackagesDetails.size();j++){
                QuestionPackageDetail questionPackageDetail = questionPackagesDetails.get(j);
                QuestionPackageDetailResDataDto questionPackageDetailResDataDto = new QuestionPackageDetailResDataDto();
                questionPackageDetailResDataDto.setId(questionPackageDetail.getId());

                QuestionBank questionBank = questionPackageDetail.getQuestionBank();
                questionPackageDetailResDataDto.setQuestionId(questionBank.getId());
                questionPackageDetailResDataDto.setQuestion(questionBank.getQuestion());

                QuestionType questionType = questionBank.getQuestionType();
                questionPackageDetailResDataDto.setTypeOfQuestion(questionType.getQuestionTypeName());

                
                // question multiple choice and count base on question bank id
                Map<OptionalBuilder,Object> optQuestionMc = new HashMap<>();

                Map<String,Object> wheresQuestionMc = new HashMap<>();
                wheresQuestionMc.put("question_bank_id", questionBank.getId());

                optQuestionMc.put(OptionalBuilder.WHERE, wheresQuestionMc);
                optQuestionMc.put(OptionalBuilder.RELATION, "AND");

                List<QuestionMultipleChoice> questionMultipleChoices = questionMultipleChoiceDao.getAllBy("tb_question_multiple_choice", QuestionMultipleChoice.class, optQuestionMc);

                List<QuestionMultipleChoiceResDataDto> multipleChoiceResDataDtos = new ArrayList<>();

                for(int k = 0;k < questionMultipleChoices.size();k++){
                    QuestionMultipleChoice questionMultipleChoice = questionMultipleChoices.get(k);

                    QuestionMultipleChoiceResDataDto questionMultipleChoiceResDataDto = new QuestionMultipleChoiceResDataDto();

                    questionMultipleChoiceResDataDto.setId(questionMultipleChoice.getId());
                    questionMultipleChoiceResDataDto.setQuestionMultipleAnswer(questionMultipleChoice.getQuestionMultipleAnswer());
                    questionMultipleChoiceResDataDto.setQuestionAnswer(questionMultipleChoice.getQuestionAnswer());

                    multipleChoiceResDataDtos.add(questionMultipleChoiceResDataDto);
                }

                questionPackageDetailResDataDto.setMultipleChoice(multipleChoiceResDataDtos);

                questionPackageDetailResDataDtos.add(questionPackageDetailResDataDto);
            }

            QuestionPackageResDataDto questionPackageResDataDto = new QuestionPackageResDataDto();
            questionPackageResDataDto.setId(questionPackage.getId());
            questionPackageResDataDto.setQuestionPackageCode(questionPackage.getQuestionPackageCode());
            questionPackageResDataDto.setQuestionPackageName(questionPackage.getQuestionPackageName());
            questionPackageResDataDto.setListOfQuestionPackageDetail(questionPackageDetailResDataDtos);

            questionPackageResDataDto.setTotalOfQuestion(countOfquestionPackagesDetail);

            questionPackageResDataDtos.add(questionPackageResDataDto);
        }

        baseListDto.setModel(QuestionPackage.class);
        baseListDto.setTotal(countOfquestionPackages);
        baseListDto.setData(questionPackageResDataDtos);

        return baseListDto;
    }


    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(QuestionPackageInsertReqDto resDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        QuestionPackage questionPackage = new QuestionPackage();

        questionPackage.setQuestionPackageCode(resDto.getQuestionPackageCode());
        questionPackage.setQuestionPackageName(resDto.getQuestionPackageName());

        questionPackage.setCreatedBy("1");

        QuestionPackage questionPackageInsert = questionPackageDao.save(questionPackage);

        if(questionPackageInsert != null){
            List<QuestionBankResDataDto> questionBankRes = resDto.getQuestionBanks();

            int countOfPackageDetail = 0;

            if(!questionBankRes.isEmpty()){
                for (int i = 0; i < questionBankRes.size(); i++) {
                    QuestionBankResDataDto questionBankResDataDto = questionBankRes.get(i);

                    QuestionBank questionBank = questionBankDao.getByIdAndDetach(QuestionBank.class, questionBankResDataDto.getId());

                    if(questionBank != null){
                        QuestionPackageDetail packageDetail = new QuestionPackageDetail();
                        packageDetail.setQuestionBank(questionBank);
                        packageDetail.setQuestionPackage(questionPackageInsert);
                        packageDetail.setCreatedBy("1");
    
                        QuestionPackageDetail questionPackageDetailInsert = questionPackageDetailDao.save(packageDetail);
    
                        if(questionPackageDetailInsert != null){
                            countOfPackageDetail ++;
                        }
                    }
                }
            }

            baseInsertResDto.setId(questionPackageInsert.getId());
            baseInsertResDto.setMessage("Data package and "+countOfPackageDetail+" question bank is saved");
        }else{
            baseInsertResDto.setMessage("Data failed to save");
        }

        return baseInsertResDto;
    }
     
    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateResDto update(QuestionPackageUpdateReqDto resDto){
        BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

        QuestionPackage questionPackage = questionPackageDao.getByIdAndDetach(QuestionPackage.class, resDto.getId());
        Optional<QuestionPackage> optQuestionPackage = Optional.ofNullable(questionPackage);

        if(optQuestionPackage.isPresent()){

            if(resDto.getQuestionPackageName() != null && !resDto.getQuestionPackageName().equals(questionPackage.getQuestionPackageName())){
                questionPackage.setQuestionPackageName(resDto.getQuestionPackageName());
            }
            
            
            if(resDto.getVersion() == questionPackage.getVersion()){
    
                QuestionPackage questionPackageUpdate = questionPackageDao.update(questionPackage);
    
                if(questionPackageUpdate != null){
    
                    // list question bank for update
                    List<QuestionBankResDataDto> questionBankResDataDtos = resDto.getQuestionBanks();
            
                    Map<OptionalBuilder,Object> optPackageDetails = new HashMap<>();
            
                    Map<String,Object> wheresPackageDetails = new HashMap<>();
                    wheresPackageDetails.put("question_package_id",resDto.getId());
            
                    optPackageDetails.put(OptionalBuilder.WHERE, wheresPackageDetails);
                    optPackageDetails.put(OptionalBuilder.RELATION, "AND");
            
                    Map<String,Object> joinsPackageDetails = new HashMap<>();
                    joinsPackageDetails.put("tb_question_bank", "id");
            
                    optPackageDetails.put(OptionalBuilder.JOIN, joinsPackageDetails);
                    optPackageDetails.put(OptionalBuilder.ID, "question_bank_id");
            
                    // list question package yang tersedia 
                    List<QuestionPackageDetail> questionPackageDetailsAvail = questionPackageDetailDao.getAllBy("tb_question_package_detail", QuestionPackageDetail.class, optPackageDetails);
            
                    int countOfQuestionBankInsert = 0;
                    int countOfQuestionBankDelete = 0;
                    if(!questionBankResDataDtos.isEmpty() && !questionPackageDetailsAvail.isEmpty()){
            
                        // insert
                        for (int i = 0; i < questionBankResDataDtos.size(); i++) {
                            QuestionBankResDataDto bankResDataDto = questionBankResDataDtos.get(i);
                            
                            for (int j = 0; j < questionPackageDetailsAvail.size(); j++) {
                                QuestionPackageDetail packageDetailAvail = questionPackageDetailsAvail.get(j);
                            
                                if(!bankResDataDto.getId().equals(packageDetailAvail.getQuestionBank().getId())){
                                    insertPackageDetail(bankResDataDto,questionPackage);
                                    countOfQuestionBankInsert++;
                                    break;
                                }
                            }                        
                        }
    
                        // delete question (optional jika ingin mengubah question bank sesuai dengan update)
                        for (int i = 0; i < questionBankResDataDtos.size(); i++) {
                            QuestionBankResDataDto bankResDataDto = questionBankResDataDtos.get(i);
                            
                            for (int j = 0; j < questionPackageDetailsAvail.size(); j++) {
                                QuestionPackageDetail packageDetailAvail = questionPackageDetailsAvail.get(j);
                            
                                if(!bankResDataDto.getId().equals(packageDetailAvail.getQuestionBank().getId())){
                                    deletePackageDetail(packageDetailAvail.getId(),"id");
                                    countOfQuestionBankDelete++;
                                    break;
                                }
                            }                        
                        }
                    }else if(!questionBankResDataDtos.isEmpty() && questionPackageDetailsAvail.isEmpty()){
            
                        for (int i = 0; i < questionBankResDataDtos.size(); i++) {
                            QuestionBankResDataDto bankResDataDto = questionBankResDataDtos.get(i);
                            insertPackageDetail(bankResDataDto,questionPackage);
                        }
                    }
    
                    baseUpdateResDto.setVersion(questionPackageUpdate.getVersion());
                    baseUpdateResDto.setMessage("Data updated with "+countOfQuestionBankInsert+" new and "+countOfQuestionBankDelete+" remove");
                }else{
                    baseUpdateResDto.setMessage("Data update is failed");
                }
        
                
            }else{
                baseUpdateResDto.setMessage("Version is not same");
            }
        }else{
            baseUpdateResDto.setMessage("Data is not available");
        }


        return baseUpdateResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseDeleteResDto softDelete(QuestionPackageDeleteReqDto resDto){
        BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

        QuestionPackage questionPackage = questionPackageDao.getByIdAndDetach(QuestionPackage.class, resDto.getId());
        Optional<QuestionPackage> optQuestionPackage = Optional.ofNullable(questionPackage);

        if(optQuestionPackage.isPresent()){
            questionPackage.setIsActive(resDto.getIsActive());

            questionPackage.setUpdatedBy("1");

            QuestionPackage questionPackageDelete = questionPackageDao.update(questionPackage);

            if(questionPackageDelete != null){
                baseDeleteResDto.setMessage("Data deleted");
            }else{
                baseDeleteResDto.setMessage("Data failed to delete");
            }
        }else{
            baseDeleteResDto.setMessage("Data is not available");
        }

        return baseDeleteResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseDeleteResDto hardDelete(QuestionPackageDeleteReqDto resDto){

        QuestionPackage questionPackage = questionPackageDao.getByIdAndDetach(QuestionPackage.class, resDto.getId());
        Optional<QuestionPackage> optQuestionPackage = Optional.ofNullable(questionPackage);

        BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

        if(optQuestionPackage.isPresent()){

            Boolean isDeleteDetail = deletePackageDetail(resDto.getId(),"question_package_id");

            if(isDeleteDetail){
                Map<OptionalBuilder,Object> optPackagesRemove = new HashMap<>();
                Map<String,Object> wheresPackageRemove = new HashMap<>();
                                    
                wheresPackageRemove.put("id", resDto.getId());

                optPackagesRemove.put(OptionalBuilder.WHERE, wheresPackageRemove);
                optPackagesRemove.put(OptionalBuilder.RELATION, "AND");
                
                Boolean isDelete = questionPackageDao.removeBy("tb_question_package",QuestionPackage.class, optPackagesRemove);

                if(isDelete){
                    baseDeleteResDto.setMessage("Data delete permanent");
                }
            }else{
                baseDeleteResDto.setMessage("Data failed to delete");
            }
        }else{
            baseDeleteResDto.setMessage("Data is not available");
        }
        
        return baseDeleteResDto;
    }

    private Boolean deletePackageDetail(String id,String key){
        Map<OptionalBuilder,Object> optPackageDetailsRemove = new HashMap<>();
        Map<String,Object> wheresPackageDetailsRemove = new HashMap<>();
        wheresPackageDetailsRemove.put(key, id);

        optPackageDetailsRemove.put(OptionalBuilder.WHERE, wheresPackageDetailsRemove);
        optPackageDetailsRemove.put(OptionalBuilder.RELATION, "AND");

        boolean isDel = questionPackageDetailDao.removeBy("tb_question_package_detail",QuestionPackageDetail.class, optPackageDetailsRemove);

        return isDel;
    }

    private void insertPackageDetail(QuestionBankResDataDto bankResDataDto,QuestionPackage questionPackage){
        QuestionBank questionBank = questionBankDao.getByIdAndDetach(QuestionBank.class, bankResDataDto.getId());
        
        if(questionBank != null){
            QuestionPackageDetail packageDetail = new QuestionPackageDetail();

            packageDetail.setQuestionBank(questionBank);
            packageDetail.setQuestionPackage(questionPackage);
            packageDetail.setCreatedBy("1");

            QuestionPackageDetail packageDetailInsert = questionPackageDetailDao.save(packageDetail);

            // message
            if(packageDetailInsert != null){

            }
        }
    }

}
