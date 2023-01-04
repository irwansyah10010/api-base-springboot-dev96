package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_question_package")
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionPackage extends BaseModel{

	@Column(name="question_package_code")
	private String questionPackageCode;
	
	@Column(name="question_package_name")
	private String questionPackageName;
}
