package com.hushunjian.gradle.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="table_a")
public class TableA {
	@ApiModelProperty(value="id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@Column(name = "name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT 'name'")
	private String name;
	
	@OneToMany(mappedBy="tableA")
	private List<TableB> tableBs;
}
