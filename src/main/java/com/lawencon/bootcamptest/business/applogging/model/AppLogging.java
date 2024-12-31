package com.lawencon.bootcamptest.business.applogging.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_app_logging")
@Data
@EqualsAndHashCode(callSuper = false)
public class AppLogging extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "source")
    private String source;

    @Column(name = "data")
    private String data;
    
}
