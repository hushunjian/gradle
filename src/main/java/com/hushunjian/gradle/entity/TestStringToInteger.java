package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(appliesTo = "test_string_to_integer",comment="测试字符串转数字")
@Entity
@BatchSize(size = 20)
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
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number0",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number0;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number1",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number1;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number2",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number2;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number3",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number3;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number4",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number4;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number5",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number5;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number6",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number6;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number7",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number7;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number8",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number8;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number9",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number9;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number10",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number10;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number11",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number11;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number12",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number12;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number13",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number13;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number14",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number14;
	
	@ApiModelProperty(value="字符串类型的数字",required=true)
	@Column(name="number15",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '数字'")
	private String number15;
}
