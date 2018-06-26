package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(name="production_plan")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class ProductionPlanEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@Column(name = "plan_start_time", columnDefinition = "datetime(6) DEFAULT NULL COMMENT '计划开始时间'")
	private ZonedDateTime planStartTime;
	
	@Column(name = "plan_end_time", columnDefinition = "datetime(6) DEFAULT NULL COMMENT '计划结束时间'")
	private ZonedDateTime planEndTime;
	
	@Column(name = "plan_belong_month", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '计划所属月份'")
	private String planBelongMonth;
	
	@Column(name = "plan_number", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '生产计划编号'")
	private String planNumber;

	@Column(name = "status", columnDefinition = "int(2) NOT NULL DEFAULT 0 COMMENT '生产计划状态(0:未实施,1:已实施,2:已结束)'")
	private Integer status;
}
