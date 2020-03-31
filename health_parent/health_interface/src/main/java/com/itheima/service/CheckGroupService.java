package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    public List<CheckItem> findAll();
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
}
