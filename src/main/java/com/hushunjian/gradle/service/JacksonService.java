package com.hushunjian.gradle.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hushunjian.gradle.dto.DevInfo2;
import com.hushunjian.gradle.dto.His2;
import com.hushunjian.gradle.util.UrlUtil;

@Service
public class JacksonService {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static{
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}
	
	public void test() {
		a();
		b();
	}
	
	
	private void a(){
		String urlStr = String.format("http://www.0531yun.cn/wsjc/Device/getDeviceData.do?userID=%s&userPassword=%s",
				"190103jmjd", "190103jmjd");
		String jsonStr = UrlUtil.connectionUrl(urlStr);
		System.out.println(jsonStr);
		try {
			DevInfo2[] beanList = mapper.readValue(jsonStr, DevInfo2[].class);
			for(DevInfo2 info : beanList){
				System.out.println(info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void b(){
		String urlStr = String.format(
				"http://www.0531yun.cn/wsjc/Device/getDevHisData.do?devKey=%s&beginTime=%s&endTime=%s&userID=%s&userPassword=%s",
				"1087073", "201906121320", "201906121410", "190103jmjd", "190103jmjd");
		String jsonStr = UrlUtil.connectionUrl(urlStr);
		System.out.println(jsonStr);
		try {
			His2 his2 = mapper.readValue(jsonStr, His2.class);
			System.out.println(his2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
