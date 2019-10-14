package com.offcn.msg.handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.msg.enty.Dept;
import com.offcn.msg.feign.DeptFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@Controller
@RequestMapping(value = "dept")
public class DeptController {

    @Autowired
    private DeptFeign deptFeign;

    @GetMapping("queryAll")
    @ResponseBody
    public List<Dept> queryAll1(){
        return deptFeign.queryAll();
    }

    @GetMapping("index")
    @ResponseBody
    public String index(){

        return deptFeign.index();
    }
}
