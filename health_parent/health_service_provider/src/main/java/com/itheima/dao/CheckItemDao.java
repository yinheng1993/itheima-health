package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    Page<CheckItem>selectByCondition(String queryString);
    public Long selectCountByCheckItemId(Integer id);
    public void delete(Integer id);
    public void edit(CheckItem checkItem);
    CheckItem findById(Integer id);
}
