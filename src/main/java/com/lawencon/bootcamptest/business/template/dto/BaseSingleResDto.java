package com.lawencon.bootcamptest.business.template.dto;



import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class BaseSingleResDto {

	@Setter @Getter
	private Object data;
	
	@Setter
	private Class<?> model;
	
	@Setter @Getter
	private List<String> condition;
	
	public String getModel(){
		return model.getSimpleName();
	}
}
