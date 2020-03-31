package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                long countByOrderDate =
                        orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (countByOrderDate > 0) {
                    //说明此时data这一个日期,已经有值了,那么执行修改方法
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    orderSettingDao.add(orderSetting);
                }
            }

        }

    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String begin = date + "-1";
        String end = date + "-31";
        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
//        System.out.println(map);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result=new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (OrderSetting orderSetting : list) {
                Map m=new HashMap();
                m.put("date",orderSetting.getOrderDate().getDate());
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(countByOrderDate>0){
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            orderSettingDao.add(orderSetting);
        }
    }
}

