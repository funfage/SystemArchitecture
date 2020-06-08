package com.gdut.zrf;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
@JsonRootName(value = "user")
public class UserWithRoot {
    public int id;
    public String name;
}
