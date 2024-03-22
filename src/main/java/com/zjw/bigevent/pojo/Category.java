package com.zjw.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章分类表
 *
 * @author 朱俊伟
 * @since 2024/03/15 20:00
 */
@Data
public class Category {
    /**
     * ID
     */
    @NotNull(groups = {Update.class})
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty
    private String categoryName;

    /**
     * 分类别名
     */
    @NotEmpty
    private String categoryAlias;

    /**
     * 创建人ID
     */
    @JsonIgnore
    private Integer createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 添加分组校验
    public interface Add extends Default {

    }

    // 更新分组校验
    public interface Update extends Default{

    }
}
