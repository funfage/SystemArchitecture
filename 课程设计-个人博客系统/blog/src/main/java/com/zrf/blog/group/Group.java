package com.zrf.blog.group;

import javax.validation.GroupSequence;

/**
 * @author 张润发
 */

@GroupSequence({Insert.class, Update.class})
public interface Group {
}
