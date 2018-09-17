package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(appliesTo = "test_string_to_integer",comment="测试字符串转数字")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class TestStringToInteger implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number;
}
