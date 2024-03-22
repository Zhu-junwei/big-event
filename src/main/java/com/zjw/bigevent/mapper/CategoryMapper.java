package com.zjw.bigevent.mapper;

import com.zjw.bigevent.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 朱俊伟
 * @since 2024/03/16 14:42
 */
@Mapper
public interface CategoryMapper {

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) values" +
            "(#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);

    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias},update_time = #{updateTime} where id = #{id}")
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);
}
