package com.lawencon.bootcamptest.business.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="tb_status_user")
@Data
public class StatusUser {
    
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "last_ip_web")
    private String lastIpWeb;

    @Column(name = "last_logout_web")
    private Long lastLogoutWeb;

    @Column(name = "last_login_web")
    private Long lastLoginWeb;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "invalid_login_counter")
    private Integer invalidLoginCounter;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "is_login_web")
    private Boolean isLoginWeb;

}
