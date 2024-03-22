package com.zjw.bigevent.controller;

import com.zjw.bigevent.pojo.Result;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author 朱俊伟
 * @since 2024/03/16 19:33
 */
@RestController
@Slf4j
public class FileUploadController {

    /**
     * 返回头像，使用的免费API
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> upload(MultipartFile file) {
        String filename = Objects.requireNonNull(file.getOriginalFilename()).substring(0, file.getOriginalFilename().lastIndexOf('.'));
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String username = (String) threadLocalMap.get("username");
        String encode = URLEncoder.encode(username + "_" + filename, StandardCharsets.UTF_8);
        String imgURL = String.format("https://api.multiavatar.com/%s.png",encode);
        log.info("上传头像：{}",imgURL);
        return Result.success(imgURL);
    }
}
