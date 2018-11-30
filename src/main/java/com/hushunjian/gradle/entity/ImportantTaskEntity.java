package com.hushunjian.gradle.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 重点任务实体
 *
 * @author hushunjian
 */
@Data
@Entity
@Table(name = "PF_IMPORTANT_TASK_T")
@DynamicInsert
@DynamicUpdate
public class ImportantTaskEntity{
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private Long id; 
	
	@Version
	@Column(name="version",columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;

    @Column(name = "name", columnDefinition = "varchar(255) not null default '' comment '重点任务名称'")
    private String name;

    @Column(name = "start_date", columnDefinition = "datetime comment '开始日期'")
    private ZonedDateTime startDate;

    @Column(name = "end_date", columnDefinition = "datetime comment '完成日期'")
    private ZonedDateTime endDate;

    @Column(name = "design_number", columnDefinition = "decimal(10,3) default 0 comment '设计工程量(由构件数量生成)'")
    private BigDecimal designNumber;

    @Column(name = "remark", columnDefinition = "varchar(255) not null default '' comment '备注'")
    private String remark;

    @Column(name = "parent_project_id", columnDefinition = "varchar(255) not null default '' comment '线路id'")
    private String parentProjectId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_task_id")
    private TaskEntity planTask;

    /**
     * 重点任务组实体,最顶级重点任务组不与总控计划关联
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ImportantTaskEntity parent;

    /**
     * 与总控计划关联的子级重点任务集合
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportantTaskEntity> children = new ArrayList<>();

    
}
