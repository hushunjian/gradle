package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(name = "jpa_1")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class JPAEntityA implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@ApiModelProperty(value="用户姓名",required=true)
	@Column(name="user_name",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名'")
	private String userName; 
}
