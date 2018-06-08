package com.hushunjian.gradle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.MeetingManage;
import com.hushunjian.gradle.repo.AccessTokenRepo;
import com.hushunjian.gradle.sendmessage.SendTemplate;
import com.hushunjian.gradle.sendmessage.SendTemplateWork;
import com.hushunjian.gradle.sendmessage.Template;
import com.hushunjian.gradle.sendmessage.WechatMpProperties;
import com.hushunjian.gradle.task.GetWeChatAccessTokenTask;
import com.hushunjian.gradle.util.WeiXinUtil;

import net.sf.json.JSONObject;

@Service
public class WeChatService {
	
	@Autowired
	private WechatMpProperties wechatMpProperties;
	@Autowired
	private MeetingManageService meetingManageService; 
	@Autowired
	private AccessTokenRepo accessTokenRepo;
	@Autowired
	private GetWeChatAccessTokenTask getWeChatAccessTokenTask;
	
	private List<MeetingManage> getAllMeetWhenTimeOverTwoFour(){
		return meetingManageService.getAllMeetWhenTimeOverTwoFour();
	}

	public void testWeChatProperties() {
		List<MeetingManage> meetingManages = getAllMeetWhenTimeOverTwoFour();
		boolean isClose = false;
		for(MeetingManage meetingManage : meetingManages){
			//发送微信消息
			StringBuilder url = new StringBuilder("http://" + wechatMpProperties.getUrl() + "/pages/meeting-detail.jsp?meetId=");
			url.append(meetingManage.getMeetId()).append("&openId=").append(meetingManage.getMeetCreatePersonOpenId());
			StringBuilder content = new StringBuilder("您的【" + meetingManage.getMeetMain());
			if(isClose){//2-4天,不需要关闭会议
				content.append("】会议已创建2天或以上,请您及时确认并结束会议");
			}else{//超过5天,需要关闭会议
				content.append("】会议已创建5天或以上,已自动关闭");
			}
			SendTemplate sendTemplate = new SendTemplateWork("系统消息",content.toString(),url.toString(),meetingManage.getMeetCreatePersonOpenId(),"办公系统",wechatMpProperties.getTaskTemplate());
			sendWeChatMessage(sendTemplate,0);
		}
	}

	private int sendWeChatMessage(SendTemplate sendTemplate, int num){
		Template template = sendTemplate.returnTemplate();
		String accessToken = accessTokenRepo.findAll().get(0).getToken();
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonResult = WeiXinUtil.httpRequest(requestUrl, "POST", template.toJSON());
        if (jsonResult != null) {
            int errorCode = jsonResult.getInt("errcode");
            switch (errorCode) {
                case 40001:
                    if (num < 5) {
                    	getWeChatAccessTokenTask.execute();
                        sendWeChatMessage(sendTemplate, ++num);
                    } else {
                        break;
                    }
                    break;
            }
            return errorCode;
        }
		return -1;
	}
}
