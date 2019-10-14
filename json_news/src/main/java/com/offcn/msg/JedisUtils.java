package com.offcn.msg;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2019/10/12.
 */
public class JedisUtils {
    private static JedisPool jedisPool;
    //静态代码块:
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(20); // 最大的闲时连接
        config.setMinIdle(5); // 最小的闲时连接
        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }
    //获取连接
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}