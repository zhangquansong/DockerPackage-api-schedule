package com.clt.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.common.dataSource.DBTypeEnum;
import com.clt.api.common.dataSource.DataSourceSwitch;
import com.clt.api.common.utils.Query;
import com.clt.api.entity.ScheduleJobLog;
import com.clt.api.mapper.qrtz.ScheduleJobLogMapper;
import com.clt.api.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {


    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;


    /**
     * 新增
     *
     * @param scheduleJobLog
     */
    @Override
    public void create(ScheduleJobLog scheduleJobLog) {
        this.insertOrUpdate(scheduleJobLog);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @Override
    public void delete(Integer id) {
        this.deleteById(id);
    }

    /**
     * 修改
     *
     * @param scheduleJobLog
     */
    @Override
    public void edit(ScheduleJobLog scheduleJobLog) {
        this.updateById(scheduleJobLog);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @DataSourceSwitch(DBTypeEnum.qrtz)
    @Override
    public List<ScheduleJobLog> listAll() {
        return this.selectList(null);
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return
     */
    @Override
    public ScheduleJobLog findById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        Page<ScheduleJobLog> page = this.selectPage(new Query<ScheduleJobLog>(params).getPage(),
                new EntityWrapper<ScheduleJobLog>().like(StringUtils.isNotBlank(jobId), "job_id", jobId));
        return page;
    }

}