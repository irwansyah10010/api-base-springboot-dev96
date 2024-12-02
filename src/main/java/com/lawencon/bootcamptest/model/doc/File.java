package com.lawencon.bootcamptest.model.doc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_file")
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends BaseModel{
	
	@Column(name = "url_file")
	private String urlFile;
	
	@Column(name = "ext")
	private String ext;
	
}
