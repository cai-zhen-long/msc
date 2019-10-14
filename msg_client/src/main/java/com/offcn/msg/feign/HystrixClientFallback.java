package com.offcn.msg.feign;



import com.offcn.msg.enty.Dept;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/10/10.
 */
@Component
public class HystrixClientFallback implements DeptFeign {

    @Override
    public List<Dept> queryAll() {
        List<Dept> list=new ArrayList<>();
        Dept dept=new Dept();
        dept.setDname("error");
        list.add(dept);
        return list;
    }

    @Override
    public String index() {
        return "error";
    }
}
