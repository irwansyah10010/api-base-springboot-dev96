package com.lawencon.bootcamptest.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity @Table(name = "tb_assign")
@Data
@EqualsAndHashCode(callSuper = false)
public class Assign extends BaseModel{

	@ManyToOne
	@JoinColumn(name = "user_candidate_id")
	private User userCandidate;
	
	@ManyToOne
	@JoinColumn(name = "user_reviewer_id")
	private User userReviewer;
}
