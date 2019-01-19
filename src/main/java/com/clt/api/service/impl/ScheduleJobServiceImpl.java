package com.clt.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.common.quartz.ScheduleUtils;
import com.clt.api.common.utils.Constant;
import com.clt.api.common.utils.Query;
import com.clt.api.entity.ScheduleJob;
import com.clt.api.mapper.qrtz.ScheduleJobMapper;
import com.clt.api.service.ScheduleJobService;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {


    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        EntityWrapper<ScheduleJob> scheduleJobEntityWrapper = new EntityWrapper<>();
        scheduleJobEntityWrapper.and("status=0").and("is_delete=0");
        List<ScheduleJob> scheduleJobList = this.selectList(scheduleJobEntityWrapper);
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public Page<ScheduleJob> queryPage(Map<String, Object> params) {
        String beanName = (String) params.get("beanName");
        Page<ScheduleJob> page = this.selectPage(new Query<ScheduleJob>(params).getPage(),
                new EntityWrapper<ScheduleJob>().like(StringUtils.isNotBlank(beanName), "bean_name", beanName));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ScheduleJob scheduleJob) {
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        this.insert(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        // 删除数据
        this.deleteBatchIds(Arrays.asList(jobIds));
    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("jobIds", jobIds);
        map.put("status", status);
        return baseMapper.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, this.selectById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }
}