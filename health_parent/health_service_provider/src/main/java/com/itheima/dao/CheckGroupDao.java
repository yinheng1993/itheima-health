package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public List<CheckItem> findAll();
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> selectByCondition(String queryString);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
//    void setCheckGroupAndCheckItem(Map map);
    void deleteAssociation(Integer id);
    void edit(CheckGroup checkGroup);
}
