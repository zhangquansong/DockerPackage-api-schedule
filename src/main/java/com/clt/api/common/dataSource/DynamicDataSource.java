package com.clt.api.common.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName : DynamicDataSource
 * @Author : zhangquansong
 * @Date : 2019/1/19 0019 上午 11:30
 * @Description :AbstractRoutingDataSource
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 取得当前使用哪个数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
