package com.itheima.controller;


import com.aliyuncs.exceptions.ClientException;
import com.itheima.Utils.SMSUtils;
import com.itheima.Utils.ValidateCodeUtils;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    //体检预约时发送手机验证码
    public Result send4Order(String telephone) {
        Integer integer = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage("SMS_177541885", telephone, integer.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码存入redis中
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 50 * 60, integer.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    //登录时发送手机验证码
    public Result send4Login(String telephone) {
        Integer integer = ValidateCodeUtils.generateValidateCode(6);
        try {
            //发送短信
            //SMSUtils.sendShortMessage("SMS_177536918", telephone, integer.toString());
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码存入redis中
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 50 * 60, integer.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
