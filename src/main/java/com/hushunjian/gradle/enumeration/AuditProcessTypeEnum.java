package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuditProcessTypeEnum {

    /**
     * 新建计划流程
     */
    ADD(0, "新建计划流程"),
    /**
     * 调整计划流程
     */
    UPDATE(1, "调整计划流程"),
    /**
     * 删除计划流程
     */
    DELETE(2, "删除计划流程");

    private int key;

    private String value;

    AuditProcessTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonCreator
    public static AuditProcessTypeEnum fromKey(@JsonProperty("key") int key) {
        for (AuditProcessTypeEnum s : AuditProcessTypeEnum.values()) {
            if (s.key == key) {
                return s;
            }
        }
        return ADD;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
