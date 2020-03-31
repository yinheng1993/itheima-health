package com.itheima;

import com.itheima.Utils.QiniuUtils;
import com.itheima.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class Jobs {

    @Autowired
    private JedisPool jedisPool;
    public void ClearImgJob(){
        //根据redis中保存的两个set集合进行差值计算,获取垃圾图片名称的集合
        Set<String>set=jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set!=null ){
            for (String picName : set) {
                //删除七牛云服务器上的额图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从redis集合中删除图片的名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
                System.out.println("自定义任务,清楚垃圾图片:"+picName);
            }
        }
    }
}
