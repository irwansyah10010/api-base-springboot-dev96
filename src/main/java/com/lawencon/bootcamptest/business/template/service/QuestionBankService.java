package com.lawencon.bootcamptest.business.template.service;
// package com.lawencon.bootcamptest.business.service;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import javax.transaction.Transactional;

// import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
// import org.springframework.stereotype.Service;

// import com.lawencon.bootcamptest.business.dao.QuestionBankDao;
// import com.lawencon.bootcamptest.business.dao.QuestionMultipleChoiceDao;
// import com.lawencon.bootcamptest.business.dao.QuestionTypeDao;
// import com.lawencon.bootcamptest.business.dto.BaseDeleteResDto;
// import com.lawencon.bootcamptest.business.dto.BaseInsertResDto;
// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.BaseUpdateResDto;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankDeleteReqData;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankInsertReqDto;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankResDataDto;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankUpdateReqDto;
// import com.lawencon.bootcamptest.business.dto.questionmultiplechoice.QuestionMultipleChoiceResDataDto;
// import com.lawencon.bootcamptest.business.model.TestCategory;
// import com.lawencon.bootcamptest.business.model.doc.QuestionBank;
// import com.lawencon.bootcamptest.business.model.doc.QuestionMultipleChoice;
// import com.lawencon.bootcamptest.constant.OptionalBuilder;

// @Service
// public class QuestionBankService {
    
//     private QuestionBankDao questionBankDao;
//     private QuestionMultipleChoiceDao questionMultipleChoiceDao;
//     private QuestionTypeDao questionTypeDao;

//     public QuestionBankService(QuestionBankDao questionBankDao,QuestionMultipleChoiceDao questionMultipleChoiceDao, QuestionTypeDao questionTypeDao){
//         this.questionBankDao = questionBankDao;
//         this.questionMultipleChoiceDao = questionMultipleChoiceDao;
//         this.questionTypeDao = questionTypeDao;
//     }

//     public BaseListResDto getAll(Integer page,Integer limit,Boolean ascending,
//         Boolean isActive){

//         // get all
// 		Map<OptionalBuilder,Object> options = new HashMap<>();

// 		Map<String,Object> wheres = new HashMap<>();
// 		Map<String,Object> joins = new HashMap<>();

// 		joins.put("tb_question_type", "id");

// 		options.put(OptionalBuilder.JOIN, joins);
// 		options.put(OptionalBuilder.ID, "question_type_id");
		
// 		wheres.put("tb_question_bank.is_active", isActive);

// 		options.put(OptionalBuilder.WHERE, wheres);
// 		options.put(OptionalBuilder.RELATION, "AND");

// 		Integer countOfQuestionBank =  questionBankDao.getCountOfAllBy("tb_question_bank", options);
		
// 		options.put(OptionalBuilder.LIMIT,limit);
// 		options.put(OptionalBuilder.PAGE,page);

//         List<QuestionBank> questionBanks = questionBankDao.getAllBy("tb_question_bank", QuestionBank.class, options);

//         List<QuestionBankResDataDto> questionBankResDatas = new ArrayList<>();
		
// 		BaseListResDto baseDto = new BaseListResDto();

//         for (int i = 0; i < questionBanks.size(); i++) {
//             QuestionBank questionBank = questionBanks.get(i);
//             TestCategory questionType = questionBank.getQuestionType();

//             QuestionBankResDataDto questionBankResDataDto = new QuestionBankResDataDto();

//             questionBankResDataDto.setId(questionBank.getId());
//             questionBankResDataDto.setQuestion(questionBank.getQuestion());
//             questionBankResDataDto.setQuestionType(questionType.getQuestionTypeName());

//             options.clear();
//             wheres.clear();

//             wheres.put("question_bank_id", questionBank.getId());

//             options.put(OptionalBuilder.WHERE, wheres);
//             options.put(OptionalBuilder.RELATION, "AND");

//             List<QuestionMultipleChoice> questionMultipleChoices = questionMultipleChoiceDao.getAllBy("tb_question_multiple_choice", QuestionMultipleChoice.class, options);
            
//             List<QuestionMultipleChoiceResDataDto> questionMultipleChoiceResDataDtos = new ArrayList<>();
//             for (int j = 0; j < questionMultipleChoices.size(); j++) {
//                 QuestionMultipleChoice questionMultipleChoice = questionMultipleChoices.get(j);

//                 QuestionMultipleChoiceResDataDto questionMultipleChoiceResDataDto = new QuestionMultipleChoiceResDataDto();

//                 questionMultipleChoiceResDataDto.setId(questionMultipleChoice.getId());
//                 questionMultipleChoiceResDataDto.setQuestionMultipleAnswer(questionMultipleChoice.getQuestionMultipleAnswer());
//                 questionMultipleChoiceResDataDto.setQuestionAnswer(questionMultipleChoice.getQuestionAnswer());

//                 questionMultipleChoiceResDataDtos.add(questionMultipleChoiceResDataDto);
//             }

//             questionBankResDataDto.setMultipleChoice(questionMultipleChoiceResDataDtos);
//             questionBankResDatas.add(questionBankResDataDto);
//         }

//         baseDto.setTotal(countOfQuestionBank);
//         baseDto.setModel(QuestionBank.class);
//         baseDto.setData(questionBankResDatas);
//         baseDto.setPage(page);
//         baseDto.setLimit(limit);

//         return baseDto;
//     }

//     @Transactional(rollbackOn = Exception.class)
//     public BaseInsertResDto save(QuestionBankInsertReqDto resDto){

//         QuestionBank questionBank = new QuestionBank();
//         BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

//         if(resDto.getQuestionTypeId() != null){
//             TestCategory questionType = questionTypeDao.getByIdAndDetach(TestCategory.class, resDto.getQuestionTypeId());
    
//             questionBank.setQuestion(resDto.getQuestion());
//             questionBank.setQuestionType(questionType);

//             questionBank.setCreatedBy("1");

//             QuestionBank questionBankInsert = questionBankDao.save(questionBank);

//             if(questionBankInsert != null){
                
//                 List<QuestionMultipleChoiceResDataDto> questionMultipleChoiceResDataDtos = resDto.getMultipleChoices();

//                 int countOfMultiple = 0;
//                 if(questionMultipleChoiceResDataDtos != null && questionType.getQuestionTypeCode().equals("m")){

//                     // save multiple choice
//                     countOfMultiple = saveMultiplechoice(questionMultipleChoiceResDataDtos,questionBankInsert);

//                     baseInsertResDto.setId(questionBankInsert.getId());
//                     baseInsertResDto.setMessage("Data question bank and "+countOfMultiple+" multiple saved");
//                 }else{
//                     baseInsertResDto.setId(questionBankInsert.getId());
//                     baseInsertResDto.setMessage("Data saved");
//                 }                
//             }else{
//                 baseInsertResDto.setMessage("Data failed to save");
//             }
//         }else{
//             baseInsertResDto.setMessage("Type id not available");
//         }

//         return baseInsertResDto;
//     }

//     @Transactional(rollbackOn = Exception.class)
//     public BaseUpdateResDto update(QuestionBankUpdateReqDto resDto){
//         BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

//         QuestionBank questionBank = questionBankDao.getByIdAndDetach(QuestionBank.class, resDto.getId());
//         Optional<QuestionBank> optQuestionBank = Optional.ofNullable(questionBank);

//         if(optQuestionBank.isPresent()){
//             if(resDto.getQuestion() != null && !questionBank.getQuestion().equals(resDto.getQuestion())){
//                 questionBank.setQuestion(resDto.getQuestion());
// 			}

//             //int countOfMultiple = 0;
//             if(resDto.getQuestionTypeId() != null  && !questionBank.getQuestionType().getId().equals(resDto.getQuestionTypeId())){
//                 TestCategory questionType = questionTypeDao.getByIdAndDetach(TestCategory.class, resDto.getQuestionTypeId());
//                 Optional<TestCategory> optQType = Optional.ofNullable(questionType);

//                 if(optQType.isPresent()){
//                     questionBank.setQuestionType(questionType);

//                     // hapus atau buat multiple choice jika question type berubah
//                     List<QuestionMultipleChoiceResDataDto> questionMultipleChoiceResDataDtos = resDto.getMultipleChoices();
//                     if(!questionMultipleChoiceResDataDtos.isEmpty()){
//                         if(questionType.getQuestionTypeCode().equals("m")){
//                             // save multiple choice
//                             //countOfMultiple = 
//                             saveMultiplechoice(questionMultipleChoiceResDataDtos,questionBank);
//                         }else{
//                             hardDeleteMc(resDto.getId());
//                         }
//                     }
//                 }

//                 questionBank.setUpdatedBy("1");
//             }

//             if(questionBank.getVersion() == resDto.getVersion()){
//                 QuestionBank questionBankUpdate = questionBankDao.update(questionBank);
    
//                 if(questionBankUpdate != null){
//                     baseUpdateResDto.setVersion(questionBankUpdate.getVersion());
//                     baseUpdateResDto.setMessage("Data updated");
//                 }else{
//                     baseUpdateResDto.setMessage("Data is failed to updated");
//                 }
//             }else{
//                 baseUpdateResDto.setMessage("Version is not same");
//             }

//         }

//         return baseUpdateResDto;
//     }


//     private Integer saveMultiplechoice(List<QuestionMultipleChoiceResDataDto> questionMultipleChoiceResDataDtos, QuestionBank questionBankInsert){
//         int countOfMultiple = 0;
//         for (int i = 0; i < questionMultipleChoiceResDataDtos.size(); i++) {
//             QuestionMultipleChoiceResDataDto questionMultipleChoiceResDataDto = questionMultipleChoiceResDataDtos.get(i);

//             QuestionMultipleChoice multipleChoice = new QuestionMultipleChoice();

//             multipleChoice.setQuestionMultipleAnswer(questionMultipleChoiceResDataDto.getQuestionMultipleAnswer());
//             multipleChoice.setQuestionAnswer(questionMultipleChoiceResDataDto.getQuestionAnswer());
//             multipleChoice.setQuestionBank(questionBankInsert);

//             multipleChoice.setCreatedBy("1");

//             QuestionMultipleChoice questionMultipleInsert = questionMultipleChoiceDao.save(multipleChoice);

//             if(questionMultipleInsert != null){
//                 countOfMultiple++;
//             }
//         }

//         return countOfMultiple;
//     }


//     @Transactional(rollbackOn = Exception.class)
//     public BaseDeleteResDto softDelete(QuestionBankDeleteReqData resDto){
//         BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

//         QuestionBank questionBank = questionBankDao.getByIdAndDetach(QuestionBank.class, resDto.getId());
//         Optional<QuestionBank> optQuestionBank = Optional.ofNullable(questionBank);

//         if(optQuestionBank.isPresent()){
//             questionBank.setIsActive(resDto.getIsActive());

//             QuestionBank questionBankUpdate =  questionBankDao.update(questionBank);

//             if(questionBankUpdate != null){
//                 baseDeleteResDto.setMessage("Data deleted");
//             }else{
//                 baseDeleteResDto.setMessage("Data failed to update");
//             }
//         }else{
//             baseDeleteResDto.setMessage("Data question bank not available");
//         }

//         return baseDeleteResDto;
//     }

//     @Transactional(rollbackOn = Exception.class)
//     public BaseDeleteResDto hardDelete(QuestionBankDeleteReqData resDto){
//         // catatan(tambahkan validasi fk di tabel lain dan berikan konfirmasi jika data berada ditabel terkait)
//         BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

//         QuestionBank questionBank = questionBankDao.getByIdAndDetach(QuestionBank.class, resDto.getId());
//         Optional<QuestionBank> optQuestionBank = Optional.ofNullable(questionBank);

//         if(optQuestionBank.isPresent()){

//             Map<OptionalBuilder,Object> opt = new HashMap<>();
    
//             Map<String,Object> wheres = new HashMap<>();
//             wheres.put("id", resDto.getId());
    
//             opt.put(OptionalBuilder.WHERE, wheres);
//             opt.put(OptionalBuilder.RELATION, "AND");
    
//             boolean accRemove =  questionBankDao.removeBy("tb_question_bank",QuestionBank.class, opt);
            
//             // acc remove
//             if(accRemove){
//                 hardDeleteMc(resDto.getId());
    
//                 baseDeleteResDto.setMessage("Data delete permanent");
//             }
//         }


//         return baseDeleteResDto;
//     }

//     public Boolean hardDeleteMc(String id){
//         Map<OptionalBuilder,Object> optMc = new HashMap<>();

//         Map<String,Object> wheresMc = new HashMap<>();
//         wheresMc.put("question_bank_id", id);

//         optMc.put(OptionalBuilder.WHERE, wheresMc);
//         optMc.put(OptionalBuilder.RELATION, "AND");

//         boolean accRemove =  questionMultipleChoiceDao.removeBy("tb_question_multiple_choice", QuestionMultipleChoice.class, optMc);
        
//         return accRemove;
//     }
// }
