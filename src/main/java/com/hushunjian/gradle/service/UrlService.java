package com.hushunjian.gradle.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hushunjian.gradle.dto.DevInfo;
import com.hushunjian.gradle.dto.His;

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
		String connectionUrl = connectionUrl(urlStr);
		List<T> result = JSON.parseArray(connectionUrl, clazz);
		return result;
	}

	private String connectionUrl(String urlStr) {
		HttpURLConnection connection;
		try {
			URL url = new URL(urlStr);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (InputStream inputStream = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			String str;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private <T> T connectionUrlToObject(String urlStr, Class<T> clazz) {
		String connectionUrl = connectionUrl(urlStr);
		return JSON.parseObject(connectionUrl, clazz);
	}

}
