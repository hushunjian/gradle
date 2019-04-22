package com.hushunjian.gradle.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExcelData implements Serializable {
    private static final long serialVersionUID = 4444017239100620999L;
    // 表头
    private List<String> titles;
    // 数据
    private List<List<Object>> rows;
    // 页签名称
    private String name;

/**
 * 需要合并的列下标(只针对单独列)
 */
@ApiModelProperty(value = "需要合并的列下标")
private List<Integer> mergedColumns;
}
