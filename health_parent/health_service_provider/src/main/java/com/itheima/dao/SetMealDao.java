package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealDao {
    public List<CheckGroup> findAll();
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String, Integer> map);
    public Page<Setmeal> findByCondition(String queryString);
    public List<Setmeal>getAllSetMeal();
    public Setmeal findById(int id);
    List<Map<String, Object>> findSetmealCount();
}
