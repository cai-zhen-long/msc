package com.offcn.msg.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.msg.enty.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@FeignClient(value = "dept",fallback = HystrixClientFallback.class)
public interface DeptFeign {
    @RequestMapping(value = "/dept/queryAll",method = RequestMethod.GET)
    //@GetMapping(value = "/dept/queryAll")
    public List<Dept> queryAll();


    @GetMapping(value = "/dept/index")
    public String index();


}
