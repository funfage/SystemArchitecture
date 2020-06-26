package com.zrf.blog.pojo;

import com.zrf.blog.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 张润发
 */
@Data
public class Music {

    @NotNull(message = "id不能为空！", groups = {Update.class})
    private Integer id;
    private String name;
    private String artist;
    private String url;
    private String cover;
    private String lrc;
    private String createdTime;
    private Integer enabled;
    private Integer deleted;

}
