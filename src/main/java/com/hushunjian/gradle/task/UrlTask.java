package com.hushunjian.gradle.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;


@Component
public class UrlTask {

	public void execute(){
        // 建立实际的连接
        HttpURLConnection connection;
        try {
        	String.format("http://www.0531yun.cn/wsjc/Device/getDeviceData.do?userID=%s&userPassword=%s", "190103jmjd", "190103jmjd");
            URL url = new URL("");
            // 打开和URL之间的连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
        try (
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
           {
        	
           } catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
