package com.offcn.msg.enty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2019/10/8.
 */
@Data
@NoArgsConstructor
public class Dept {
    private int deptno;
    private String dname;
    private String db_source;
}
