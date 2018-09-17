package com.hushunjian.gradle.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryMeetingRequest {
	
	@ApiModelProperty(value = "会议议程")
	private String meetMain;
	
}
