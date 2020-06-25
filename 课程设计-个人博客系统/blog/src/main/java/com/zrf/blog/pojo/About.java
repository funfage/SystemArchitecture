package com.zrf.blog.pojo;

import com.zrf.blog.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 张润发
 */
@Data
public class About implements Serializable {

    @NotNull(message = "aboutId不能为空！", groups = {Update.class})
    private Integer aboutId;
    private String aboutTitle;
    private String aboutContent;
    private Integer aboutRead;
    private String createdTime;
    private String updateTime;
    @NotNull(message = "version不能为空", groups = {Update.class})
    private Integer version;
    private Integer enable;
    private Integer deleted;

}
