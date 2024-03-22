package com.zjw.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBean <T>{
    private Long total;//总条数
    private List<T> items;//当前页数据集合
}
