package com.lawencon.bootcamptest.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_question_package_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionPackageDetail  extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name="question_package_id")
	private QuestionPackage questionPackage;
	
	@ManyToOne
	@JoinColumn(name="question_bank_id")
	private QuestionBank questionBank;
}
