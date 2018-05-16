package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="user")
@Entity
public class User {
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
    private Long id;  
  
	@Column(name="user_name",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名'")
	private String userName; 
	
	@Column(name="age",columnDefinition="int(3) NOT NULL DEFAULT '30' COMMENT '年龄'")
	private int age;  
	
	@Column(name="created_by",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '创建人'")
	private String createdBy;  
	
	@Column(name="created_time",columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'")
	private String createdTime;  
	
	@Column(name="updated_by",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '修改人'")
	private String updatedBy;  
	
	@Column(name="updated_time",columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
	private String updatedTime;  
	
}
