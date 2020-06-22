package com.gdut.zrf;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
public class RawBean {

    public String name;

    @JsonRawValue
    public String json;

}
