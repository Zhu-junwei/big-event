package com.zjw.bigevent.service;

import com.zjw.bigevent.pojo.Category;

import java.util.List;

/**
 * 分类业务层接口
 *
 * @author 朱俊伟
 * @since 2024/03/16 14:42
 */
public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);

    void deleteById(Integer id);
}
