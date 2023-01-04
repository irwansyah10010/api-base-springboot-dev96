package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_question_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionType extends BaseModel{

	@Column(name="question_type_code")
	private String questionTypeCode;
	
	@Column(name="question_type_name")
	private String questionTypeName;
}
