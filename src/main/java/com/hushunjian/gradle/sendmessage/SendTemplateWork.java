package com.hushunjian.gradle.sendmessage;

import java.io.Serializable;
import java.util.Date;

import com.hushunjian.gradle.util.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class SendTemplateWork extends SendTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String messageType;//消息类型

    private String userName;//发起消息的用户名称

    private String content;//发送的模板消息的内容

    private String url;//发送的模板消息跳转的链接

    public SendTemplateWork(String messageType, String content, String url, String openId, String userName, String templateId) {
        this.messageType = messageType;
        this.content = content;
        this.url = url;
        this.openId = openId;
        this.userName = userName;
        this.templateId = templateId;
    }

    @Override
    public Template returnTemplate() {
        Template template = new Template();
        String dateSystem = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        paras.add(new TemplateParam("first", "您有新的事件通知消息: ", "#FF3333"));
        paras.add(new TemplateParam("keyword1", "" + messageType + "", "#0044BB"));
        paras.add(new TemplateParam("keyword2", "" + userName + "", "#0044BB"));//user
        paras.add(new TemplateParam("keyword3", "" + dateSystem + "", "#0044BB"));
        paras.add(new TemplateParam("keyword4", "" + content + "", "#0044BB"));
        paras.add(new TemplateParam("Remark", "请及时进行处理!", "#AAAAAA"));
        template.setTemplateParamList(paras);
        template.setTemplateId(templateId);
        template.setToUser(openId);
        template.setTopColor("#00DD00");
        if (url != null) {
            template.setUrl(url);
        }
        return template;
    }
}
