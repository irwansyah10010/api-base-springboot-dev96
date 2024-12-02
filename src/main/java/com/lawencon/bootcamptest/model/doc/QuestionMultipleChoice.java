package com.lawencon.bootcamptest.model.doc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_question_multiple_choice")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionMultipleChoice extends BaseModel{

	@Column(name = "question_multiple_answer")
	private String questionMultipleAnswer;

	@Column(name = "question_answer")
	private Boolean questionAnswer;
	
	@ManyToOne
	@JoinColumn(name="question_bank_id")
	private QuestionBank questionBank;
}
