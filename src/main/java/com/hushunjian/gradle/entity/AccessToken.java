package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Created by huyongzhi on 2017/7/12.
 */

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 微信通用接口凭证
 */
@Entity
@Component
@Table(name="PF_ACCESS_TOKEN_T")
@Data
@DynamicInsert
@DynamicUpdate
public class AccessToken {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id'")
    private long id;

    @Column(name="token",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '获取到的凭证'")
    private String token;

    @Column(name="expires_in",columnDefinition="int(10) NOT NULL DEFAULT 7200 COMMENT '凭证有效时间(默认有效时间为两小时),单位:秒'")
    private int expiresIn;
}
