package com.gdut.zrf.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gdut.zrf.pojo.Item;
import com.gdut.zrf.pojo.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/8
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    /**
     * 返回的json中不带有ownerName
     * @return
     */
    @GetMapping("/get1")
    @JsonView(Views.Public.class)
    public List<Item> getAllItemsWithoutOwnerName() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "项目1", "张三");
        Item item2 = new Item(2, "项目2", "李四");
        items.add(item1);
        items.add(item2);
        return items;
    }

    /**
     * 返回的json中带有ownerName
     * @return
     */
    @GetMapping("/get2")
    @JsonView(Views.Internal.class)
    public List<Item> getAllItemsWithOwnerName() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "项目1", "张三");
        Item item2 = new Item(2, "项目2", "李四");
        items.add(item1);
        items.add(item2);
        return items;
    }

}
