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
@Table(name="MEETING_PROJECT_RELATION")
@ApiModel(value="会议人员信息表")
@DynamicInsert
public class MeetingProjectRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@ApiModelProperty(value="会议id",required=true)
	@Column(name = "meet_id",columnDefinition="bigint(20) NOT NULL DEFAULT -1  COMMENT '会议id'")
    private Long meetId;
	
	@ApiModelProperty(value="关联项目id",required=true)
	@Column(name="meet_project_id",columnDefinition="varchar(255) NOT NULL DEFAULT ''  COMMENT '关联项目id'")
	private String meetProjectId;
	
	@ApiModelProperty(value="关联项目名称",required=true)
	@Column(name="meet_project_name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '关联项目名称'")
	private String meetProjectName;
}
