package com.clt.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.clt.api.entity.ScheduleJobLog;

import java.util.List;
import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLog> {

    /**
     * 新增
     *
     * @param scheduleJobLog
     */
    void create(ScheduleJobLog scheduleJobLog);

    /**
     * 删除
     *
     * @param id 主键id
     */
    void delete(Integer id);

    /**
     * 修改
     *
     * @param scheduleJobLog
     */
    void edit(ScheduleJobLog scheduleJobLog);


    /**
     * 列表(全部)
     *
     * @return
     */
    List<ScheduleJobLog> listAll();

    /**
     * 通过id获取数据
     *
     * @param id 主键id
     * @return
     */
    ScheduleJobLog findById(Integer id);

    Page queryPage(Map<String, Object> params);
}