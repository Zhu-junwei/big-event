package com.zjw.bigevent.service;

import com.zjw.bigevent.pojo.User;

/**
 * @author 朱俊伟
 * @since 2024/03/15 20:06
 */
public interface UserService {
    User findByName(String userName);

    void register(String userName, String password);

    Integer update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
