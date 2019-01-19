package com.clt.api;

import com.clt.api.service.UserService;
import com.clt.api.service.ScheduleJobLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
  *@ClassName : DataTest
  *@Author : zhangquansong
  *@Date : 2019/1/19 0019 下午 2:10
  *@Description :
  **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;
    @Autowired
    
    private UserService userService;

    @Test
    public void test() {
        userService.selectList(null).stream().forEach(item -> System.out.println(item));
        scheduleJobLogService.selectList(null).stream().forEach(item -> System.out.println(item));
//        User user = userService.findById(14);
//        System.out.println(user);
//        scheduleJobLogService.listAll().stream().forEach(item -> System.out.println(item));
    }

}
