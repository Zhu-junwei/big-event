package com.zjw.bigevent.service;

import com.zjw.bigevent.pojo.Article;
import com.zjw.bigevent.pojo.PageBean;

/**
 * @author 朱俊伟
 * @since 2024/03/16 17:00
 */
public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    int delete(Integer id);

    int update(Article article);
}
