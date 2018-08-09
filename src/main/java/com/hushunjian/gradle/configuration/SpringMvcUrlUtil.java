package com.hushunjian.gradle.configuration;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
@Component
public class SpringMvcUrlUtil implements ServletContextListener {

	 @Autowired
	 private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	     Set<RequestMappingInfo> requestMappingInfos = requestMappingHandlerMapping.getHandlerMethods().keySet();
	        for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
	            System.out.println(requestMappingInfo.getPatternsCondition().toString());
	        }
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
}
