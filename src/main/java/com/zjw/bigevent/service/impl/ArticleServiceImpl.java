package com.zjw.bigevent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjw.bigevent.mapper.ArticleMapper;
import com.zjw.bigevent.pojo.Article;
import com.zjw.bigevent.pojo.PageBean;
import com.zjw.bigevent.service.ArticleService;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 朱俊伟
 * @since 2024/03/16 17:01
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void add(@RequestBody @Validated Article article) {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        Integer userId = (Integer) threadLocalMap.get("id");
        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageHelper.offsetPage((pageNum-1) * pageSize, pageSize);
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        Integer userId = (Integer) threadLocalMap.get("id");
        PageInfo<Article> pageInfo = new PageInfo<>(articleMapper.list(userId,categoryId, state));
        return PageBean.<Article>builder()
                .total(pageInfo.getTotal())
                .items(pageInfo.getList())
                .build();
    }

    @Override
    public int delete(Integer id) {
        return articleMapper.delete(id);
    }

    @Override
    public int update(Article article) {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        Integer userId = (Integer) threadLocalMap.get("id");
        article.setCreateUser(userId);
        return articleMapper.update(article);
    }
}
