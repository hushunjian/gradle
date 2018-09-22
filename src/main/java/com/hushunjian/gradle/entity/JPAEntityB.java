package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import javax.persistence.Table;

import com.hushunjian.gradle.enumeration.CourseEnum;

import lombok.Data;

@Data
@Table(name = "jpa_2")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class JPAEntityB implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@Column(name="user_id",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '用户主键id'")
	private String userId; 
	
    @Enumerated(EnumType.STRING)
    @Column(name="course",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '用户主键id'")
	private CourseEnum course;
    
	@Column(name="score",columnDefinition="decimal(3,1) NOT NULL DEFAULT '0.0' COMMENT '分数'")
	private BigDecimal score;
}
