package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="table_b")
public class TableB {
	@ApiModelProperty(value="id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@Column(name = "name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT 'name'")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "tableA_id",columnDefinition="bigint(20) NOT NULL COMMENT 'table_a表主键id'")
	private TableA tableA;
}
