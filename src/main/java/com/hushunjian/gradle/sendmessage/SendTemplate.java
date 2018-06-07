package com.hushunjian.gradle.sendmessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public abstract class SendTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String templateId;//模板id
    protected String openId;//接收消息的用户openid
    protected int num;
    protected List<TemplateParam> paras = new ArrayList<TemplateParam>();//可变参数列表
    public abstract Template returnTemplate();//返回微信的模板
}
