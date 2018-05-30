package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@Entity
@Table(name="meeting_receipt_info")
@ApiModel(value="会议回执消息表")
@DynamicInsert
public class MeetingReceiptInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="会议回执主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "receipt_id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long receiptId;
	
	@ApiModelProperty(value="参与会议人员信息表主键id",required=true)
	@Column(name="meeting_person_relation_id",columnDefinition="bigint(20) NOT NULL DEFAULT -1 COMMENT '参与会议人员信息表主键id'")
	private Long meetingPersonRelationId;
	
	@ApiModelProperty(value="回执情况 (0：参加 1：不参加 2:待回执)",required=true)
	@Column(name="receipt_status",columnDefinition="int(3) NOT NULL DEFAULT 2 COMMENT '回执情况 (0：参加 1：不参加 2:待回执)'")
	private String receiptStatus;
	
	@ApiModelProperty(value="回执信息",required=true)
	@Column(name="receipt_info",columnDefinition="varchar(255) NOT NULL DEFAULT '待回执' COMMENT '回执信息'")
	private String receiptInfo;
}
