package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Blog;
import com.zrf.blog.service.BlogService;
import com.zrf.blog.utils.Page;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.StringUtils;
import com.zrf.blog.vo.BlogVo;
import com.zrf.blog.vo.TimeLineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 保存
     * @param blog
     *
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody Blog blog) {
        blogService.save(blog);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询
     */
    @GetMapping("/get/{id}")
    public Result<Blog> get(@PathVariable @NotEmpty(message = "id不能为空！") String id) {
        if (StringUtils.isBlank(id)) {
            return new Result<>(ResultEnum.PARAMS_NULL, "blogId");
        }
        Blog blog = blogService.getById(id);
        return new Result<>(blog);
    }

    /**
     * 更新
     *
     * @param blog
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody Blog blog) {
        blogService.update(blog);
        return new Result<>("更新成功！");
    }

    /**
     * 根据id阅读
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/read/{id}")
    public Result<BlogVo> read(@PathVariable @NotEmpty(message = "id不能为空！") String id) {
        BlogVo blog = blogService.readById(id);
        return new Result<>(blog);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable @NotEmpty(message = "id不能为空！") String id) {
        blogService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"blog_goods", "blog_read", "blog_collection",
                    "type_name", "blog_comment", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR, "排序参数");
            }
        }
        page = blogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 查询时间轴
     *
     * @return
     */
    @RequestMapping(value = "/getTimeLine", method = RequestMethod.GET)
    public Result<List<TimeLineVo>> getTimeLine() {
        List<TimeLineVo> timeList = new ArrayList<>(16);
        List<BlogVo> blogVoList = blogService.getTimeLine();
        blogVoList.forEach(e -> {
            String blogMonth = e.getBlogMonth();
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonth(blogMonth);
            if(timeList.contains(timeLineVo)) {
                // 取出对应的数据
                TimeLineVo timeLine = getTimeLineForList(timeList, timeLineVo);
                List<BlogVo> list = timeLine.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLine.setList(list);
            } else {
                List<BlogVo> list = timeLineVo.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLineVo.setList(list);
                timeList.add(timeLineVo);
            }
        });
        return new Result<>(timeList);
    }

    /**
     * 根据博客id和当前登录用户查询点赞记录
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getGood/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getGood(@PathVariable String blogId) {
        int count = blogService.getGoodsCount(blogId);
        return new Result<>(count);
    }

    /**
     * 获取对应的timeLine
     * @param timeList
     * @param timeLineVo
     * @return
     */
    private TimeLineVo getTimeLineForList(List<TimeLineVo> timeList, TimeLineVo timeLineVo) {
        for (TimeLineVo lineVo : timeList) {
            if(timeLineVo.getMonth().equals(lineVo.getMonth())) {
                return lineVo;
            }
        }
        return null;
    }

}
