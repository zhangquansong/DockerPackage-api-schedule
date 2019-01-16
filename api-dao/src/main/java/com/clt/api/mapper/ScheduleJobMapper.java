package com.clt.api.mapper;

import com.clt.api.base.SuperMapper;
import com.clt.api.entity.ScheduleJob;

import java.util.Map;

/**
 * @author zhangquansong
 * @since 2019-01-14
 */
public interface ScheduleJobMapper extends SuperMapper<ScheduleJob> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}