package com.lawencon.bootcamptest.business.template.model;
// package com.lawencon.bootcamptest.business.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.PrePersist;
// import javax.persistence.Table;

// import com.lawencon.bootcamptest.base.model.BaseEntity;

// import lombok.Data;
// import lombok.EqualsAndHashCode;


// // umum, penjuruan(IT, kesehatan dll)
// @Entity
// @Table(name="tb_test_category")
// @Data
// @EqualsAndHashCode(callSuper = false)
// public class TestCategory extends BaseEntity{

// 	@Column(name="test_code")
// 	private String testTypeCode;
	
// 	@Column(name="test_name")
// 	private String testTypeName;

// 	@Column(name = "is_active")
// 	private Boolean isActive;

// 	@PrePersist
// 	public void prePersistLocal(){
// 		this.isActive = true;
// 	}
// }
