package com.zjw.bigevent.mapper;

import com.zjw.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 朱俊伟
 * @since 2024/03/16 17:00
 */
@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,article.category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Delete("delete from article where id=#{id}")
    int delete(Integer id);

    int update(Article article);
}
