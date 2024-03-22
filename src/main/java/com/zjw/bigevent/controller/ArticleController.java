package com.zjw.bigevent.controller;

import com.zjw.bigevent.pojo.Article;
import com.zjw.bigevent.pojo.PageBean;
import com.zjw.bigevent.pojo.Result;
import com.zjw.bigevent.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 朱俊伟
 * @since 2024/03/15 22:18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result<Object> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    /**
     * 分页查询文章
     */
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state) {
        PageBean<Article> articlePageBean = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(articlePageBean);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        articleService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody
                         Article article) {
        articleService.update(article);
        return Result.success();
    }
}
