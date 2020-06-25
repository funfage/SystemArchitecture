package com.zrf.blog.controller;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Type;
import com.zrf.blog.service.TypeService;
import com.zrf.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/type")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加type
     *
     * @param type
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody Type type) {
        typeService.save(type);
        return new Result<>("添加成功！");
    }

    /**
     * 后台查询所有
     *
     * @return
     */
    @GetMapping("/listBack")
    public Result<List<Type>> listBack() {
        List<Type> typeList = typeService.getAll();
        return new Result<>(typeList);
    }

    /**
     * 前台查询所有
     *
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Type>> getList() {
        List<Type> typeList = typeService.getTypeList();
        return new Result<>(typeList);
    }

    /**
     * 根据id更新
     *
     * @param type
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody Type type) {
        typeService.update(type);
        return new Result<>("更新成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<Object> getById(@PathVariable @Min(value = 0, message = "id的值必须大于0！") Integer id) {
        Type type = typeService.getById(id);
        return new Result<>(type);
    }

    /**
     * 根据id启用
     *
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    public Result<Object> enable(@PathVariable @Min(value = 0, message = "id的值必须大于0！") Integer id) {
        typeService.enableById(id);
        return new Result<>("已启用！");
    }

    /**
     * 根据id弃用
     *
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    public Result<Object> disable(@PathVariable @Min(value = 0, message = "id的值必须大于0！") Integer id) {
        typeService.disableById(id);
        return new Result<>("已弃用！");
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable @Min(value = 0, message = "id的值必须大于0！") Integer id) {
        typeService.deleteById(id);
        return new Result<>("删除成功！");
    }

}
