package com.zjw.bigevent.service.impl;

import com.zjw.bigevent.mapper.CategoryMapper;
import com.zjw.bigevent.pojo.Category;
import com.zjw.bigevent.service.CategoryService;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 文章分类服务
 *
 * @author 朱俊伟
 * @since 2024/03/16 14:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        Integer userId = (Integer) threadLocalMap.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        Integer userId = (Integer) threadLocalMap.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
