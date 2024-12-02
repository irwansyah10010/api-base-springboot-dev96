package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;
import com.lawencon.bootcamptest.model.doc.QuestionPackageDetail;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_score")
@Data
@EqualsAndHashCode(callSuper = false)
public class Score extends BaseModel{
    
	@Column(name = "score")
	private Float score;

    @ManyToOne
	@JoinColumn(name="question_package_detail_id")
	private QuestionPackageDetail questionPackage;
	
	@ManyToOne
	@JoinColumn(name="assign_detail_id")
	private AssignDetail assignDetail;
}
