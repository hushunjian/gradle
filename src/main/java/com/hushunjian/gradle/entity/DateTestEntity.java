package com.hushunjian.gradle.entity;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "PF_DATE_T")
@DynamicInsert
@DynamicUpdate
public class DateTestEntity {
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id; 

    @Column(name = "send_time", columnDefinition = "datetime comment '信息发送时间'")
    private Date sendTime;
    
    @Column(name = "send_time_str", columnDefinition = "varchar(50) not null default '' comment '用户id'")
    private String timeStr;
    
    @Column(name = "send_time_a", columnDefinition = "datetime comment '信息发送时间'")
    private Date sendTimeA;
    
    @Column(name = "send_time_b", columnDefinition = "datetime comment '信息发送时间'")
    private ZonedDateTime sendTimeB;
    
    @Column(name = "send_time_b_str", columnDefinition = "datetime comment '信息发送时间'")
    private String sendTimeBStr;
}
