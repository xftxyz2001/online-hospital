package com.nwu.base.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description 分页查询结果
 * @DateTime 2024/3/1 14:07
 **/
@Data
public class PageResult<T> implements Serializable {
    //结果列表
    private List<T> items;
    //总记录数
    private long counts;
    //当前页码
    private long page;
    //每页记录数
    private long pageSize;

    public PageResult(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }
}
