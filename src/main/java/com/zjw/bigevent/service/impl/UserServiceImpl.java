package com.zjw.bigevent.service.impl;

import com.zjw.bigevent.mapper.UserMapper;
import com.zjw.bigevent.pojo.User;
import com.zjw.bigevent.service.UserService;
import com.zjw.bigevent.utils.Md5Util;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 朱俊伟
 * @since 2024/03/15 20:06
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        return userMapper.findByName(userName);
    }

    @Override
    public void register(String userName, String password) {
        password = Md5Util.getMD5String(password);
        userMapper.register(userName, password);
    }

    @Override
    public Integer update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        Integer updated = userMapper.update(user);
        if (!Integer.valueOf(1).equals(updated)){
            log.warn("更新记录数量：" + updated);
        }
        return updated;
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> threadLocal = ThreadLocalUtil.get();
        Integer id = (Integer) threadLocal.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> threadLocal = ThreadLocalUtil.get();
        Integer id = (Integer) threadLocal.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }


}
