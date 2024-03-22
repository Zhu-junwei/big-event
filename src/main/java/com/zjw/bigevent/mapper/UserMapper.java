package com.zjw.bigevent.mapper;

import com.zjw.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 朱俊伟
 * @since 2024/03/15 20:06
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{userName}")
    User findByName(String userName);

    @Insert("insert into `user`(username, password, create_time, update_time)" +
            " value(#{userName}, #{password}, now(), now()) ")
    void register(String userName, String password);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where username = #{username}")
    Integer update(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    Integer updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
