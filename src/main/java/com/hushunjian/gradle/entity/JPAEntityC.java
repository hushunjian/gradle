package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;

@Data
@Table(name = "jpa_3")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class JPAEntityC implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@Column(name="name",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '姓名'")
	private String name;
	
	@Version
	@Column(name="version",columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;
}
