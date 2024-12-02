package com.lawencon.bootcamptest.model.person;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_reviewer")
@Data
@EqualsAndHashCode(callSuper = false)
public class Reviewer extends BaseModel{
    
    @ManyToOne
	@JoinColumn(name="user_id")
    private User user;

	@JoinColumn(name="note")
    private String note;
}
