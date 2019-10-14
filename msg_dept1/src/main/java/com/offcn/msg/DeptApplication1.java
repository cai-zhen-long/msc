package com.offcn.msg;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2019/10/8.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.offcn.msg.mapper")
public class DeptApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(DeptApplication1.class,args);
    }

}
