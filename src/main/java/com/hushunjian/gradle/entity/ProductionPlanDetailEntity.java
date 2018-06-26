package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	生产计划表详情表
 */
@Data
@Entity
@Table(name="production_plan_detail")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = false)
public class ProductionPlanDetailEntity {
	
	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id; 
	
	@Column(name = "beam_span_num",columnDefinition="bigint(20) NOT NULL DEFAULT -1 COMMENT '梁跨生产编号'")
	private Long beamSpanNum;

	@Column(name = "beam_span_type", columnDefinition = "varchar(255) NOT NULL DEFAULT '25米' COMMENT '梁块类型(25米,30米)'")
	private String beamSpanType;

	@Column(name = "order_num",columnDefinition="int(10) NOT NULL DEFAULT 0 COMMENT '生产顺序'")
	private Integer order;
	
	@Column(name = "relation_pore_span_num", columnDefinition = "varchar(255) NOT NULL DEFAULT '未关联孔跨号' COMMENT '关联孔跨号编号'")
	private String relationPoreSpanNum;
	
	@Column(name = "beam_block_interval", columnDefinition = "varchar(255) NOT NULL DEFAULT '0,0' COMMENT '梁块起始编号'")
	private String beamBlockInterval;
	
	@Column(name = "relation_pedestal_num", columnDefinition = "varchar(255) NOT NULL DEFAULT '未关联台座' COMMENT '关联台座编号'")	
	private String relationPedestalNum;
	
	@Column(name = "marketers_out",columnDefinition="int(2) NOT NULL DEFAULT 1 COMMENT '梁块二维码输出(0:否,1:是)'")
	private Integer marketersOut;
	
	@Column(name = "production_plan_id", columnDefinition = "varchar(255) NOT NULL DEFAULT '未关联生产计划' COMMENT '生产计划主键id'")	
	private String productionPlanId;
}
