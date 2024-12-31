package com.lawencon.bootcamptest.business.template.service;
// package com.lawencon.bootcamptest.business.service;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.Map.Entry;

// import com.lawencon.bootcamptest.business.dao.AssignDetailDao;
// import com.lawencon.bootcamptest.business.dao.QuestionPackageDetailDao;
// import com.lawencon.bootcamptest.business.dao.ScoreDao;
// import com.lawencon.bootcamptest.business.dto.BaseDeleteResDto;
// import com.lawencon.bootcamptest.business.dto.BaseInsertResDto;
// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.BaseUpdateResDto;
// import com.lawencon.bootcamptest.business.dto.score.ScoreInsertReqDto;
// import com.lawencon.bootcamptest.business.dto.score.ScoreResDataDto;
// import com.lawencon.bootcamptest.business.dto.score.ScoreUpdateReqDto;
// import com.lawencon.bootcamptest.business.model.AssignDetail;
// import com.lawencon.bootcamptest.business.model.Score;
// import com.lawencon.bootcamptest.business.model.doc.QuestionPackageDetail;
// import com.lawencon.bootcamptest.constant.OptionalBuilder;

// public class ScoreService {

//     private ScoreDao scoreDao;
//     private AssignDetailDao assignDetailDao;
//     private QuestionPackageDetailDao questionPackageDetailDao;

//     public ScoreService(ScoreDao scoreDao,AssignDetailDao assignDetailDao,QuestionPackageDetailDao questionPackageDetailDao){
//         this.scoreDao = scoreDao;
//         this.assignDetailDao = assignDetailDao;
//         this.questionPackageDetailDao = questionPackageDetailDao;
//     }
    
//     public BaseListResDto getAll(Integer page,Integer limit,Boolean ascending,
//     Boolean isActive){
//         BaseListResDto baseListResDto = new BaseListResDto();

//         Map<OptionalBuilder,Object> options = new HashMap<>();

//         Map<String,Object> wheres = new HashMap<>();
//         Map<String,Object> joins = new HashMap<>();

//         Map<OptionalBuilder,Object> assignCustomTable = new HashMap<>();
//         assignCustomTable.put(OptionalBuilder.ID_OTHER, "assign_detail_id");
//         assignCustomTable.put(OptionalBuilder.TABLE_OTHER, "tb_score");

//         assignCustomTable.put(OptionalBuilder.ID_FK, "id");
//         joins.put("tb_assign_detail", assignCustomTable);

//         Map<OptionalBuilder,Object> packageCustomTable = new HashMap<>();
//         packageCustomTable.put(OptionalBuilder.ID_OTHER, "package_detail_id");
//         packageCustomTable.put(OptionalBuilder.TABLE_OTHER, "tb_score");

//         packageCustomTable.put(OptionalBuilder.ID_FK, "id");
//         joins.put("tb_package_detail", packageCustomTable);

//         wheres.put("tb_score.is_active", isActive);

// 		options.put(OptionalBuilder.WHERE, wheres);
// 		options.put(OptionalBuilder.RELATION, "AND");

// 		Integer countOfScore =  scoreDao.getCountOfAllBy("tb_score", options);
		
// 		options.put(OptionalBuilder.LIMIT,limit);
// 		options.put(OptionalBuilder.PAGE,page);
        
//         List<Score> scores = scoreDao.getAllBy("tb_score", Score.class, options);

//         List<ScoreResDataDto> scoreResDataDtos = new ArrayList<>();


//         for (int i = 0; i < scoreResDataDtos.size(); i++) {
            
//         }

//         baseListResDto.setModel(Score.class);
//         baseListResDto.setTotal(countOfScore);
//         baseListResDto.setData(scores);
//         baseListResDto.setPage(page);
//         baseListResDto.setLimit(limit);

//         return baseListResDto;
//     }

//     public BaseInsertResDto save(ScoreInsertReqDto scoreInsertReqDto){
//         BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

//         QuestionPackageDetail questionPackageDetail = questionPackageDetailDao.getByIdAndDetach(QuestionPackageDetail.class, scoreInsertReqDto.questionPackageDetailId);

//         Map<String,Object> mapQuestionPackageDetailIds = scoreInsertReqDto.mapQuestionPackageDetailIds;

//         if(mapQuestionPackageDetailIds.isEmpty()){
//             Score score = new Score();
//             AssignDetail assignDetail = assignDetailDao.getByIdAndDetach(AssignDetail.class, scoreInsertReqDto.getAssignDetailId());
    
//             if(assignDetail != null && questionPackageDetail != null){
//                 score.setAssignDetail(assignDetail);
//                 score.setQuestionPackage(questionPackageDetail);
//                 score.setScore(scoreInsertReqDto.getScore());
        
//                 score.setCreatedBy("1");
        
//                 Score scoreInsert = scoreDao.save(score);
        
//                 if(scoreInsert != null){
//                     baseInsertResDto.setId(scoreInsert.getId());
//                     baseInsertResDto.setMessage("data saved");
//                 }else{
//                     baseInsertResDto.setMessage("Data failed to save");
//                 }
//             }else{
//                 baseInsertResDto.setMessage("detail not available");
//             }
//         }else{

//             for(Entry<String,Object> packageDetail:mapQuestionPackageDetailIds.entrySet()){

//             }

//         }

//         return baseInsertResDto;
//     }
    
//     public BaseUpdateResDto update(ScoreUpdateReqDto scoreUpdateReqDto){
//         BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

//         Score score = scoreDao.getByIdAndDetach(Score.class, scoreUpdateReqDto.getId());
// 		Optional<Score> optScore = Optional.ofNullable(score);

//         if(score.getVersion() == scoreUpdateReqDto.getVersion()){

//             if(optScore.isPresent()){
    
//                 if(scoreUpdateReqDto.getScore() != null && !scoreUpdateReqDto.getScore().equals(score.getScore())){
//                     score.setScore(scoreUpdateReqDto.getScore());
//                 }
    
//                 Score scoreUpdate = scoreDao.update(score);
    
//                 if(scoreUpdate != null){
//                     baseUpdateResDto.setVersion(scoreUpdate.getVersion());
//                     baseUpdateResDto.setMessage("Data is updated");
//                 }
//             }else{
//                 baseUpdateResDto.setMessage("data score isnt available");
//             }
//         }else{
//             baseUpdateResDto.setMessage("Version isnt same");
//         }

//         return baseUpdateResDto;
//     }

//     public BaseDeleteResDto hardDelete(){
//         return null;
//     }
// }
