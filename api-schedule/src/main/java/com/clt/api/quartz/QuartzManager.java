package com.clt.api.quartz;

import com.google.common.base.Optional;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

/**
 * @ClassName : QuartzManager
 * @Author : zhangquansong
 * @Date : 2019/1/14 0014 下午 1:50
 * @Description :定时任务管理类
 **/
public class QuartzManager {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();


    /**
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @Description: 移除一个任务
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));// 停止触发器
            sched.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));// 移除触发器
            sched.deleteJob(new JobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param time             时间设置，参考quartz说明文档
     * @param map              传递参数
     * @Description: 添加一个定时任务
     */
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String time, Map<Object, Object> map) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            JobDetail jobDetail = (JobDetail) JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName);// 任务名，任务组，任务执行类
            if (Optional.fromNullable(map).isPresent()) {
                jobDetail.getJobDataMap().put("map", new JobDataMap(map));
            }
            // 触发器
            //设置触发器的执行时间
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(cronScheduleBuilder).build();// 触发器名,触发器组
            sched.scheduleJob(jobDetail, trigger);
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}