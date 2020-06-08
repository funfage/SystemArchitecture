package com.gdut.zrf;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
public class Item {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;

}
