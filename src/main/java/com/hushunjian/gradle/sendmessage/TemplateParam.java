package com.hushunjian.gradle.sendmessage;

import lombok.Data;

@Data
public class TemplateParam {
	// 参数名称
    private String name;
    // 参数值
    private String value;
    // 颜色
    private String color;
	public TemplateParam(String name, String value, String color) {
		super();
		this.name = name;
		this.value = value;
		this.color = color;
	}
}
