package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 目录关系表
 *
 * @author hushunjian
 */
@Data
@Entity
@Table(name = "PF_DOCUMENT_RELATION_T")
@DynamicInsert
@DynamicUpdate
public class DocumentTypeRelationEntity{
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id; 
	
	@Version
	@Column(name="version", columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;

	@Column(name="chosen_doc_id", columnDefinition="varchar(255) not null default '' COMMENT '所选中的目录id'")
	private String chosenDocId;
	
	@Column(name="subordinate_doc_id", columnDefinition="varchar(255) not null default '' COMMENT '所选中的目录下级id'")
	private String subordinateDocId;
	
	@Column(name = "type", columnDefinition = "varchar(255) not null default '' comment '层次类型'")
	private String type;
	
	@Column(name = "template", columnDefinition = "varchar(255) not null default '' comment '模板'")
	private String template;
	
	@Column(name="parent", columnDefinition="varchar(255) DEFAULT null COMMENT '父级id'")
	private String parent;
	
	@Column(name = "parent_project_id", columnDefinition = "varchar(255) not null default '' comment '线路id'")
	private String parentProjectId;
	
	@Column(name = "first_level", columnDefinition = "varchar(255) not null default '' comment '第一层级'")
	private String firstLevel;
}
