package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_question_bank")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionBank extends BaseModel{

	@Column(name = "question")
	private String question;
	
	@ManyToOne
	@JoinColumn(name = "question_type_id")
	private QuestionType questionType;
	
}
