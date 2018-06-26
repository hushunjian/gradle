package com.hushunjian.gradle.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddProductionPlanDetailRequest {
	@ApiModelProperty(value="梁跨生产编号",required=true)
	private Long beamSpanNum;

	@ApiModelProperty(value="梁块类型(25米,30米)",required=true)
	private String beamSpanType;

	@ApiModelProperty(value="生产顺序",required=true)
	private Integer orderNum;
	
	@ApiModelProperty(value="关联孔跨号编号",required=true)
	private String relationPoreSpanNum;
	
	@ApiModelProperty(value="梁块起始编号",required=true)
	private String beamBlockInterval;
	
	@ApiModelProperty(value="关联台座编号",required=true)
	private String relationPedestalNum;
	
	@ApiModelProperty(value="梁块二维码输出(0:否,1:是)",required=true)
	private Integer marketersOut;
	
	@ApiModelProperty(value="生产计划主键id",required=true)
	private String productionPlanId;
}
