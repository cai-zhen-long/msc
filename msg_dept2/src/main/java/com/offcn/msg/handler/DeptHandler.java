package com.offcn.msg.handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.msg.enty.Dept;
import com.offcn.msg.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@RestController
@RequestMapping(value = "/dept")
public class DeptHandler {
    @Autowired
    private DeptMapper deptMapper;

    @GetMapping("/queryAll")
    public List<Dept> queryAll(){
        return deptMapper.queryAll();
    }

}
