package com.hushunjian.gradle.sendmessage;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SendTemplateMeet extends SendTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	private String meetTime;//会议时间
    private String meetAddress;//会议地址
    private String messageType;//消息类型
    private String content;//发送的模板消息的内容
    private String url;//发送的模板消息跳转的链接
   
    public SendTemplateMeet(String meetTime, String meetAddress, String messageType, String content, String url, String openId, String templateId) {
        this.meetTime = meetTime;
        this.meetAddress = meetAddress;
        this.messageType = messageType;
        this.content = content;
        this.url = url;
        this.openId = openId;
        this.templateId = templateId;
    }

    @Override
    public Template returnTemplate() {
        Template template = new Template();
        template.setTemplateId(templateId);
        template.setTopColor("#00DD00");
        template.setToUser(openId);
        if (url != null) {
            template.setUrl(url);
        }        
        paras.add(new TemplateParam("first", "您有新的会议通知消息: ", "#FF3333"));
        paras.add(new TemplateParam("keyword1", "" + messageType + "", "#0044BB"));
        paras.add(new TemplateParam("keyword2", "" + meetTime + "", "#0044BB"));
        paras.add(new TemplateParam("keyword3", "" + meetAddress + "", "#0044BB"));
        paras.add(new TemplateParam("keyword4", "" + content + "", "#0044BB"));
        paras.add(new TemplateParam("Remark", "请及时进行处理!", "#AAAAAA"));
        template.setTemplateParamList(paras);
        return template;
    }
}
