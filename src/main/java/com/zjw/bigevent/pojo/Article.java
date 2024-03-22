package com.zjw.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zjw.bigevent.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

/**
 * 文章表
 *
 * @author 朱俊伟
 * @since 2024/03/15 20:04
 */
@Data
public class Article {

    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    @URL
    private String coverImg;
    @State //自定义校验注解
    private String state;
    @NotNull
    private Integer categoryId;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
