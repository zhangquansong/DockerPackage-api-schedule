package com.clt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient//通过注解@EnableEurekaClient 表明自己是一个eurekaclient.
@SpringBootApplication
@EnableTransactionManagement
@EnableHystrix
@EnableHystrixDashboard
public class ApiScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiScheduleApplication.class, args);
    }

}

