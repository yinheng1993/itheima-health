package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealService {
    public List<CheckGroup> findAll();
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult findPage(QueryPageBean queryPageBean);
    public List<Setmeal> getAllSetMeal();
    public Setmeal findById(int id);
    List<Map<String, Object>> findSetmealCount();
}

