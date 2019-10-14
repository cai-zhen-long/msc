package com.offcn.msg;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Administrator on 2019/10/11.
 */
public class RedisApplication {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.set("v1","1");//(key值相同时会覆盖原来的值 类似于map集合)
        jedis.set("v2","2");
        jedis.append("v2","3");//在原有的值的基础上添加新的值
        long len= jedis.strlen("v2");//获取字符串的长度
        jedis.incr("v2");  //对应的value的值自动加1
        jedis.decr("v1");

        jedis.append("v2","asdfg");

        String result=jedis.getrange("v2",1,3);//获取value的一部分
        jedis.setrange("v2",2,"cai"); //从起始位置开始替换value长度的数据
        System.out.println(jedis.get("v2"));

        jedis.setrange("v2",2,"adcd");

        jedis.setex("v2",5,"22");  //设定一组值 同时设定存活时间

        Long setnx = jedis.setnx("v2", "v1");//设定永久存活的一组值(key值冲突无法存入值 返回值是0)
        System.out.println(setnx);

        Thread.sleep(6000);
        System.out.println(jedis.get("v2"));//一次设定多组值   如果key存在也会覆盖

        System.out.println(jedis.get("v1"));//一次获取多个key所对应的值  得到的是一个数组


        //jedis.msetnx();一次设定多组的key值  如果有Key值存在无法完成添加
        jedis.mset("a","a","b","b","c","c1" );
        System.out.println(jedis.mget("a","c"));
    }

    @Test
    public void listTest(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.lpush("list","a","b","c");  //向集合中添加内容  相当于栈
        jedis.rpush("list1","a1","b1","c1");
        System.out.println(jedis.lindex("list",2));
        System.out.println(jedis.lpop("list"));
        System.out.println(jedis.rpop("list1"));

        System.out.println(jedis.lrange("list",0,-1));
        jedis.lpush("list4","a","b","c","d");

        System.out.println(jedis.lrange("list4",0,-1));

      jedis.del("list","list1","list2","list4");


    }


    @Test
    public void setTest(){
        Jedis jedis=new Jedis("127.0.0.1",6379);

        jedis.del("set1");
        //添加值
        jedis.sadd("set1","c","a","b");
        //获取值
        Set<String> set1 = jedis.smembers("set1");
        System.out.println(jedis.scard("set1"));
        System.out.println(set1);
        //删除单个值
        jedis.srem("set1","a");
        Set<String> setall = jedis.smembers("set1");
        //判断某个值是否存在
        boolean flag=jedis.sismember("set1","b");
        System.out.println(setall);
        System.out.println(flag);

        jedis.sadd("set2","");
        jedis.smove("set1","set2","b");
        System.out.println(set1);
        System.out.println(jedis.smembers("set2"));
    }

    @Test
    public void HashApplication(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.hset("hash1","name","张三");//存放一组键值对
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("des","班长");
        hashMap.put("age","22");
        jedis.hmset("hash1",hashMap);//设定多组键值对
        System.out.println(jedis.hgetAll("hash1"));//获取所有的数据
        System.out.println(jedis.hmget("hash1","des"));//获取map中某个键所对应的值
        System.out.println(jedis.hlen("hash1"));//当前key有几组对应的键值对

        System.out.println(jedis.hkeys("hash1"));//获取所有的key
        System.out.println(jedis.hvals("hash1"));

    }



    @Test
    private void userTest(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        List<Student> list=new ArrayList<Student>();
        Student student=new Student("1","a");
    }

}
