package com.lawencon.bootcamptest.business.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.lawencon.bootcamptest.base.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_file")
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(name = "file")
	private String file;
	
	@Column(name = "ext")
	private String ext;
	
}
