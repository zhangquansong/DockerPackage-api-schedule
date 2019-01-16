package com.clt.api.config;

import com.clt.api.service.ScheduleJobLogService;
import com.clt.api.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class ScheduleJobInvokService {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 发布调度服务
     *
     * @return
     */
    @Bean("/scheduleJobService")
    public HessianServiceExporter scheduleJobService() {
        HessianServerProxyExporter exporter = new HessianServerProxyExporter();
        exporter.setService(scheduleJobService);
        exporter.setServiceInterface(ScheduleJobService.class);
        return exporter;
    }

    /**
     * 发布调度服务日志管理
     *
     * @return
     */
    @Bean("/scheduleJobLogService")
    public HessianServiceExporter scheduleJobLogService() {
        HessianServerProxyExporter exporter = new HessianServerProxyExporter();
        exporter.setService(scheduleJobLogService);
        exporter.setServiceInterface(ScheduleJobLogService.class);
        return exporter;
    }
}
