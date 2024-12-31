package com.lawencon.bootcamptest.business.template.service;
// package com.lawencon.bootcamptest.business.service;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.lawencon.bootcamptest.business.dao.QuestionTypeDao;
// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.questiontype.QuestionTypeResDataDto;
// import com.lawencon.bootcamptest.business.model.TestCategory;

// @Service
// public class QuestionTypeService {

// 	private QuestionTypeDao questionTypeDao;
	
// 	public QuestionTypeService(QuestionTypeDao questionTypeDao) {
// 		this.questionTypeDao = questionTypeDao;
// 	}
	
// 	public BaseListResDto getAll() {
// 		List<TestCategory> questionTypes = questionTypeDao.getAllBy("tb_question_type", TestCategory.class, new HashMap<>());
		
// 		List<QuestionTypeResDataDto> questionTypeResDataDtos = new ArrayList<>();
// 		BaseListResDto baseListDto = new BaseListResDto();
		
// 		for(int i = 0;i < questionTypes.size();i++) {
// 			TestCategory questionType = questionTypes.get(i);
			
// 			QuestionTypeResDataDto questionTypeResDataDto = new QuestionTypeResDataDto();
// 			questionTypeResDataDto.setId(questionType.getId());
// 			questionTypeResDataDto.setQuestionTypeCode(questionType.getQuestionTypeCode());
// 			questionTypeResDataDto.setQuestionTypeName(questionType.getQuestionTypeName());
			
// 			questionTypeResDataDtos.add(questionTypeResDataDto);
// 		}
		
// 		baseListDto.setData(questionTypeResDataDtos);
// 		baseListDto.setModel(TestCategory.class);
		
// 		return baseListDto;
// 	}
// }
