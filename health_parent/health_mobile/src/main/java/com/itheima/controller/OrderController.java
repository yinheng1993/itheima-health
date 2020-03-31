package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.Utils.SMSUtils;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        //获取预约页面的预约电话号码
        String telephone = (String) map.get("telephone");
        //从redis中获取缓存的验证码,key为手机号加上预约板块的常量号001
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
//        System.out.println(validateCode);
        //校验手机验证码是否一样
        Result result = null;
        if (codeInRedis != null && validateCode != null && codeInRedis.equals(validateCode)) {
            //验证码比对,一样,成功
            try {
                map.put("orderType", Order.ORDERTYPE_WEIXIN);
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if (result.isFlag()) {
                //预约成功,发送短信通知
                String orderDate = (String) map.get("orderDate");
                try {
                    SMSUtils.sendShortMessage("SMS_177545616", telephone, "123456");
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            //验证码比对,不一样,失败
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        try {
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
