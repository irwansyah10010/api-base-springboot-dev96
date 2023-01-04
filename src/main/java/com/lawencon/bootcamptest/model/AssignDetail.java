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
@Table(name="tb_assign_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class AssignDetail extends BaseModel{

	@Column(name="score")
	private Float score;
	
	@Column(name="note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name="question_package_id")
	private QuestionPackage questionPackage;
	
	@ManyToOne
	@JoinColumn(name="assign_id")
	private Assign assign;
	
}
