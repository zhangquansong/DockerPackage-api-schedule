package com.clt.api.task;

import com.clt.api.common.utils.RedissLock;
import com.clt.api.entity.User;
import com.clt.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : CouponTask
 * @Author : zhangquansong
 * @Date : 2019/1/17 0017 上午 10:23
 * @Description :劵包业务定时器
 **/
@Slf4j
@Component("userTask")
public class UserTask {

    @Autowired
    private UserService userService;
    @Autowired
    private RedissLock redissLock;

    public void userTransfer() {
        User user2 = userService.findById(15);
        Long user2_version = user2.getUserVersion();
        BigDecimal user2_asset = user2.getUserAsset();

        boolean isGetLock = redissLock.tryLock("userTransfer", TimeUnit.SECONDS, 5, 10); //尝试获取锁，等待5秒，自己获得锁后一直不解锁则10秒后自动解锁
        if (isGetLock) {
            if (user2_asset.intValue() >= 100) {
                log.info("================================开始转账================================\n");
                User user1 = userService.findById(14);
                Long user1_version = user1.getUserVersion();
                BigDecimal user1_asset = user1.getUserAsset();

                log.info("用户 ： {} ，剩余资产 ：{} \n", user2.getUserLoginName(), user2.getUserAsset());
                user2.setUpdateTime(new Date());
                user2.setUserAsset(user2_asset.subtract(new BigDecimal(100)));
                user2.setUserVersion(user2_version + 1);
                userService.editUserWithVersion(user2, user2_version);
                log.info("用户 ： {} ，出账 100 。 剩余资产 ：{} \n", user2.getUserLoginName(), user2.getUserAsset());

                user1.setUpdateTime(new Date());
                user1.setUserAsset(user1_asset.add(new BigDecimal(100)));
                user1.setUserVersion(user1_version + 1);
                userService.editUserWithVersion(user1, user1_version);
                log.info("用户 ： {} ，入账 100 。 剩余资产 ：{} \n", user1.getUserLoginName(), user1.getUserAsset());
                log.info("================================转账成功结束================================\n");

            }
        }
    }

}
