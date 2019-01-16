package com.clt.api.quartz;


import com.clt.api.entity.ScheduleJobLog;
import com.clt.api.helper.SpringContextUtils;
import com.clt.api.service.ScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName : ScheduleJob
 * @Author : zhangquansong
 * @Date : 2019/1/14 0014 下午 1:51
 * @Description :定时任务
 **/
@Slf4j
public class ScheduleJob extends QuartzJobBean {
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        com.clt.api.entity.ScheduleJob scheduleJob = new com.clt.api.entity.ScheduleJob();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(com.clt.api.entity.ScheduleJob.JOB_PARAM_KEY), scheduleJob);
        // 获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        // 数据库保存执行记录
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        // 任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            ScheduleJob.log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();
            // 任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);
            // 任务状态 0：成功 1：失败
            log.setStatus(0);

            ScheduleJob.log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            ScheduleJob.log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            // 任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            // 任务状态 0：成功 1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.insert(log);
        }
    }
}
