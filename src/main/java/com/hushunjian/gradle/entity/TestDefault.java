package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="test_default")
@ApiModel(value="测试不同数据类型的默认值")
@DynamicInsert
@DynamicUpdate
public class TestDefault {

	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@ApiModelProperty(value="状态(0:失效;1:有效)",required=true)
	@Column(name="status_a",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '状态(0:失效;1:有效)'")
    private String statusA; 
	
	@ApiModelProperty(value="状态(0:失效;1:有效)",required=true)
	@Column(name="status_b",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '状态(0:失效;1:有效)'")
    private Integer statusB;
	
	@ApiModelProperty(value="状态(0:失效;1:有效)",required=true)
	@Column(name="status_c",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '状态(0:失效;1:有效)'")
    private int statusC;
}
