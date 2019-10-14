package com.offcn.msg.mapper;

import com.offcn.msg.enty.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@Mapper
public interface DeptMapper {
    public List<Dept> queryAll();
}
