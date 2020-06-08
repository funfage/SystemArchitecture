package com.gdut.zrf.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;

}
