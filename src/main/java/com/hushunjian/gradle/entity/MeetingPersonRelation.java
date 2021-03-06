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
@Table(name="MEETING_PERSON_RELATION")
@ApiModel(value="会议人员信息表")
@DynamicInsert
public class MeetingPersonRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@ApiModelProperty(value="会议id",required=true)
	@Column(name = "meet_id",columnDefinition="bigint(20) NOT NULL DEFAULT -1  COMMENT '会议id'")
    private Long meetId;
	
	@ApiModelProperty(value="参与会议人员id",required=true)
	@Column(name="meet_person_id",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '参与会议人员id'")
    private String meetPersonId;
	
	@ApiModelProperty(value="参与会议人员名称",required=true)
	@Column(name="meet_person_name",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '参与会议人员id'")
    private String meetPersonName;
	
	@ApiModelProperty(value="参与会议人员角色",required=true)
	@Column(name="meet_person_role",columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '参与会议人员角色'")
	private String meetPersonRole;
	
	@ApiModelProperty(value="参与会议人员所属单位",required=true)
	@Column(name="meet_person_depart",columnDefinition="varchar(100) NOT NULL DEFAULT '' COMMENT '参与会议人员所属单位'")
	private String meetPersonDepart;

	@ApiModelProperty(value="参与会议人员手机号",required=true)
	@Column(name="meet_phone_number",columnDefinition="varchar(11) NOT NULL DEFAULT '' COMMENT '参与会议人员手机号'")
    private String meetPhoneNumber;
	
	@ApiModelProperty(value="参与会议人员openId",required=true)
	@Column(name="meet_person_open_id",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '参与会议人员openId'")
	private String meetPersonOpenId;
}
