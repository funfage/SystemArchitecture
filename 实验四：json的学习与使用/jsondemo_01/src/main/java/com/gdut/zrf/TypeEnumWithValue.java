package com.gdut.zrf;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@AllArgsConstructor
public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    // standard constructors

    @JsonValue
    public String getName() {
        return name;
    }
}
