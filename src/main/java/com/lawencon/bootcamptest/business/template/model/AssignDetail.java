package com.lawencon.bootcamptest.business.template.model;
// package com.lawencon.bootcamptest.business.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import com.lawencon.bootcamptest.base.model.BaseEntity;
// import com.lawencon.bootcamptest.business.model.doc.QuestionPackage;

// import lombok.Data;
// import lombok.EqualsAndHashCode;

// @Entity
// @Table(name="tb_assign_detail")
// @Data
// @EqualsAndHashCode(callSuper = false)
// public class AssignDetail extends BaseEntity{

// 	@Column(name="accumulation_score")
// 	private Float score;
	
// 	@Column(name="note")
// 	private String note;

// 	@Column(name="status")
// 	private String status;
	
// 	@ManyToOne
// 	@JoinColumn(name="question_package_id")
// 	private QuestionPackage questionPackage;
	
// 	@ManyToOne
// 	@JoinColumn(name="assign_id")
// 	private Assign assign;
	
// }
