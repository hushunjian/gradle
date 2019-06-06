package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuditProcessStatusEnum {

    /**
     * 已完成
     */
    FINISHED(1, "已完成"),
    /**
     * 未完成
     */
    UNFINISHED(0, "未完成");

    private int key;

    private String value;

    AuditProcessStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonCreator
    public static AuditProcessStatusEnum fromKey(@JsonProperty("key") int key) {
        for (AuditProcessStatusEnum s : AuditProcessStatusEnum.values()) {
            if (s.key == key) {
                return s;
            }
        }
        return FINISHED;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
