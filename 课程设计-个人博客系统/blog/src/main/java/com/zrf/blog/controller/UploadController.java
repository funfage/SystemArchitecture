package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 张润发
 * @date 2020/6/10
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadImage")
    public Result<String> uploadImage(MultipartFile file) {
        if (file == null) {
            return new Result<>(ResultEnum.ERROR.getCode(), "图片文件为空！");
        }
        String url = uploadService.uploadImage(file);
        return new Result<>("上传成功！", url);
    }

}
