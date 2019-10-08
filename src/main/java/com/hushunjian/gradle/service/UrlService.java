package com.hushunjian.gradle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hushunjian.gradle.dto.DevInfo;
import com.hushunjian.gradle.dto.His;
import com.hushunjian.gradle.util.UrlUtil;

@Service
public class UrlService {

	public void test() {
		String url = String.format("http://www.0531yun.cn/wsjc/Device/getDeviceData.do?userID=%s&userPassword=%s",
				"190103jmjd", "190103jmjd");
		List<DevInfo> result = connectionUrlToObjectList(url, DevInfo.class);
		result.forEach(dev -> System.out.println(dev));

		String urlStr = String.format(
				"http://www.0531yun.cn/wsjc/Device/getDevHisData.do?devKey=%s&beginTime=%s&endTime=%s&userID=%s&userPassword=%s",
				"1087073", "201906121320", "201906121410", "190103jmjd", "190103jmjd");
		His dataInfo = connectionUrlToObject(urlStr, His.class);
		System.out.println(dataInfo);
	}

	private <T> List<T> connectionUrlToObjectList(String urlStr, Class<T> clazz) {
		String connectionUrl = UrlUtil.connectionUrl(urlStr);
		List<T> result = JSON.parseArray(connectionUrl, clazz);
		return result;
	}
	private <T> T connectionUrlToObject(String urlStr, Class<T> clazz) {
		String connectionUrl = UrlUtil.connectionUrl(urlStr);
		return JSON.parseObject(connectionUrl, clazz);
	}
	

}
