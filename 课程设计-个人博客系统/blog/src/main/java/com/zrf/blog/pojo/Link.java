package com.zrf.blog.pojo;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 友情链接
 * @author 张润发
 */
@Data
public class Link {

    @NotNull(message = "linkId不能为空！", groups = {Update.class})
    private Integer linkId;
    @NotNull(message = "链接名称不能为空！", groups = {Insert.class, Update.class})
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    @NotNull(message = "version不能为空！", groups = {Update.class})
    private Integer version;
    private Integer deleted;

}
