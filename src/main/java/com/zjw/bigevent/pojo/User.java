package com.zjw.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表
 * @author 朱俊伟
 * @since 2024/03/15 19:58
 */
@Data
public class User {

    @NotNull
    private Integer id;
    private String username;
    @JsonIgnore // 不需要返回前端
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,16}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
