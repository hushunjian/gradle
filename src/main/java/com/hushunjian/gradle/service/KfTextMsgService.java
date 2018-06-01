package com.hushunjian.gradle.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.sendmessage.SendTemplate;

@Service	
@SuppressWarnings("all")
public class KfTextMsgService implements EnvironmentAware{

	private Environment environment;
	
	public Object sendWXTemplateMsg(SendTemplate sendTemplate, int num){
		return null;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
