package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult findPage(QueryPageBean queryPageBean);
    public void delete(Integer id) throws RuntimeException;
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);

}
