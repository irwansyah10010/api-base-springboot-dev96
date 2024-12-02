package com.lawencon.bootcamptest.model.doc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;
import com.lawencon.bootcamptest.model.TestCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;

// berisi pertanyaan
@Entity
@Table(name="tb_question_bank")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionBank extends BaseModel{

	@Column(name = "question")
	private String question;
	
	@ManyToOne
	@JoinColumn(name = "test_category_id")
	private TestCategory testCategory;
	
}
