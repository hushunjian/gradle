package com.hushunjian.gradle.sendmessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class WechatMpProperties {
	@Value("${wechat.mp.appId}")
	private String appId;
	@Value("${wechat.mp.secret}")
	private String secret;
	@Value("${wechat.mp.token}")
	private String token;
	@Value("${wechat.mp.aesKey}")
	private String aesKey;
	@Value("${wechat.mp.url}")
	private String url;
	@Value("${wechat.mp.userUrl}")
	private String userUrl;
	@Value("${wechat.mp.tokenGetType}")
	private String tokenGetType;
	@Value("${wechat.mp.taskTemplate}")
	private String taskTemplate;
	@Value("${wechat.mp.meetTemplate}")
	private String meetTemplate;
	@Value("${wechat.mp.meetRemindTemplate}")
	private String meetRemindTemplate;
	@Value("${wechat.mp.remarkTemplate}")
	private String remarkTemplate;
}
