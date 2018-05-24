package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(appliesTo = "user",comment="用户表A")
@Entity
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@ApiModelProperty(value="用户姓名",required=true)
	@Column(name="user_name",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名'")
	private String userName; 
	
	@ApiModelProperty(value="年龄",required=true)
	@Column(name="age",columnDefinition="int(3) NOT NULL DEFAULT '30' COMMENT '年龄'")
	private int age;  
	
	@ApiModelProperty(value="创建人",required=true)
	@Column(name="created_by",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '创建人'")
	private String createdBy;  
	
	@ApiModelProperty(value="创建时间",required=true)
	@Column(name="created_time",columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'")
	private String createdTime;  
	
	@ApiModelProperty(value="修改人",required=true)
	@Column(name="updated_by",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '修改人'")
	private String updatedBy;  
	
	@ApiModelProperty(value="修改时间",required=true)
	@Column(name="updated_time",columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
	private String updatedTime;

	@ApiModelProperty(value="用户状态",required=true)
	@Column(name="status",columnDefinition="int(2) NOT NULL DEFAULT 1 COMMENT '用户状态,0:禁用;1:正常'")
	private int status;
}
