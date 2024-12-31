package com.lawencon.bootcamptest.business.template.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class BaseListResDto {

	@Setter @Getter
	private List<?> data;

	@Setter
	private Class<?> model;
	
	@Setter @Getter
	private Integer page;
	
	@Setter @Getter
	private Integer limit;
	
	@Setter @Getter
	private Integer total;
	
	@Setter @Getter
	private List<String> condition;

	public String getModel(){
		return model.getSimpleName();
	}
}
