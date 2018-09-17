package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum YesOrNoEnum {

    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");

    private int key;

    private String value;

    YesOrNoEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonCreator
    public static YesOrNoEnum fromKey(@JsonProperty("key") int key) {
        for (YesOrNoEnum s : YesOrNoEnum.values()) {
            if (s.key == key) {
                return s;
            }
        }
        return YES;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
