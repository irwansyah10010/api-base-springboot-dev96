package com.lawencon.bootcamptest.model.doc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.model.TestCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_answer_bank")
@Data
@EqualsAndHashCode(callSuper = false)
public class AnswerBank {
    
    @Column(name = "answer")
	private String answer;
	
	@ManyToOne
	@JoinColumn(name = "test_category_id")
	private TestCategory testCategory;
	
}