package com.clt.api.mapper.qrtz;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.clt.api.entity.ScheduleJob;

import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}