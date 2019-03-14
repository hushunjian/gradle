package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.hushunjian.gradle.enumeration.DocumentTypeEnum;

import lombok.Data;

/**
 * 目录基础数据表
 *
 * @author hushunjian
 */
@Data
@Entity
@Table(name = "PF_DOCUMENT_T")
@DynamicInsert
@DynamicUpdate
public class DocumentEntity{
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id; 
	
	@Version
	@Column(name="version", columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;

    @Column(name = "name", columnDefinition = "varchar(255) not null default '' comment '名称'")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="document_type", columnDefinition="varchar(50) NOT NULL DEFAULT '' COMMENT '文档类型'")
	private DocumentTypeEnum documentType;
}
