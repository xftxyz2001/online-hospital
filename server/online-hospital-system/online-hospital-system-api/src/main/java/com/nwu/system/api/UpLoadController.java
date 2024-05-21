package com.nwu.system.api;

import com.nwu.base.model.Result;
import com.nwu.base.utils.AliOSSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@Api(tags = "上传图片接口")
@RequestMapping("/upload")
public class UpLoadController {
    @Autowired
    AliOSSUtils aliOSSUtils;

    @PostMapping("")
    public Result upload(MultipartFile image) throws IOException {

        System.err.println(image);
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }

}
