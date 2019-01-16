package com.clt.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.clt.api.entity.ScheduleJob;
import com.clt.api.utils.PageInfo;

import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

    Page<ScheduleJob> queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     */
    void save(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     */
    void update(ScheduleJob scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}