package com.clt.api.task;

import com.alibaba.fastjson.JSON;
import com.clt.api.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName : TestTask
 * @Author : zhangquansong
 * @Date : 2019/1/16 0016 下午 1:55
 * @Description :测试定时任务
 **/
@Slf4j
@Component("MiaolicaiTask")
public class TestTask {

    public void test() {
        log.info("我是不带参数的test方法，正在被执行，参数为");
        System.out.println("<><><><><><<>");
        log.info("测试调用miaolicai_manage查询结果:{}", JSON.toJSONString("....."));
    }

    public void test2(String params, User user) {
        log.info("我是带参数的test2方法，正在被执行" + params);
    }

}
