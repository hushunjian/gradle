package com.hushunjian.gradle.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hushunjian.gradle.copier.WeChatMapper;
import com.hushunjian.gradle.entity.AccessToken;
import com.hushunjian.gradle.repo.AccessTokenRepo;
import com.hushunjian.gradle.sendmessage.WechatMpProperties;
import com.hushunjian.gradle.util.WeiXinUtil;

import net.sf.json.JSONObject;

@Transactional
@Component
public class GetWeChatAccessTokenTask {
	private static Logger log = LoggerFactory.getLogger(GetWeChatAccessTokenTask.class);
	@Autowired
	private WechatMpProperties wechatMpProperties;
	@Autowired
	private AccessTokenRepo accessTokenRepo;
	
	//@Scheduled(fixedDelay = 2 * 60 * 60 * 1000)//每隔两小时取一次
	public void execute(){
		AccessToken accessToken = new AccessToken();
		if(StringUtils.isEmpty(wechatMpProperties.getTokenGetType())){
			//发送请求获取token
			accessToken = WeiXinUtil.getAccessToken(wechatMpProperties.getAppId(),wechatMpProperties.getSecret());
		}else{
            try {
                URL url = new URL("http://" + wechatMpProperties.getTokenGetType() + "/accessToken/getAccessToken");
                // 打开和URL之间的连接
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 建立实际的连接
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                StringBuffer buffer = new StringBuffer();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                connection.disconnect();
                JSONObject json = JSONObject.fromObject(buffer.toString());
                if (json != null) {
                    accessToken.setToken(json.getString("token"));
                }
            } catch (Exception e) {
            	log.info("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
		}
		//将accessToken存入数据库
		List<AccessToken> accessTokens = accessTokenRepo.findAll();
		if(accessTokens.size() > 0){
			WeChatMapper.INSTANCE.copyProperties(accessTokens.get(0), accessToken);
		}
		accessTokenRepo.save(accessToken);
	}
}
