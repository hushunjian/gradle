package com.hushunjian.gradle.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil {
	
	public static String connectionUrl(String urlStr) {
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
}
