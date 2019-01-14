package com.hushunjian.gradle.request;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="test对象",description="对象test")
public class User1Request {
	
	@NotBlank(message="阿斯顿发送到发送")
	private String userName1;

	@ApiModelProperty(value = "user2Request")
	@Valid
	private User2Request user2Request;
}
