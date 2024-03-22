package com.zjw.bigevent.controller;

import com.zjw.bigevent.pojo.Result;
import com.zjw.bigevent.pojo.User;
import com.zjw.bigevent.service.UserService;
import com.zjw.bigevent.utils.JwtUtil;
import com.zjw.bigevent.utils.Md5Util;
import com.zjw.bigevent.utils.RedisUtil;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户控制层
 *
 * @author 朱俊伟
 * @since 2024/03/15 20:07
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByName(username);
        if (user != null) {
            return Result.error("用户名已存在");
        }
        userService.register(username, password);
        return Result.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByName(username);
        if (user == null || !Md5Util.checkPassword(password, user.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        Map<String, Object> claims = new HashMap<>() {{
            put("id", user.getId());
            put("username", user.getUsername());
        }};
        // 生成token
        String token = JwtUtil.genToken(claims);
        // 存入redis
        redisUtil.set("token:" + user.getId()+":"+user.getUsername(), token, 60 * 60 * 24);
        return Result.success(token);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByName(username);
        return Result.success(user);
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)){
            return Result.error("密码不能为空");
        }
        if (!Objects.equals(newPwd, rePwd)){
            return Result.error("两次输入密码不一致");
        }

        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String username = (String) threadLocalMap.get("username");
        Integer id = (Integer) threadLocalMap.get("id");
        User user = userService.findByName(username);
        if (!Md5Util.checkPassword(oldPwd, user.getPassword())) {
            return Result.error("原密码错误");
        }
        userService.updatePwd(newPwd);
        // 清空redis token
        redisUtil.delete("token:" + id + ":" + username);
        return Result.success();
    }
}
