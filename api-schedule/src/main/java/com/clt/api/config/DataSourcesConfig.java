package com.clt.api.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.clt.api.datasources.DBTypeEnum;
import com.clt.api.datasources.DynamicDataSource;
import com.clt.api.utils.MyMetaObjectHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan({"com.clt.api.mapper"})
public class DataSourcesConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 在方法上注解configurationProperties时，将会把属性注入到返回结果的bean中
     */
    @Bean("bizDataSource")
    @ConfigurationProperties("spring.datasource.druid.biz")
    public DruidDataSource bizDataSource() throws SQLException {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 在方法上注解configurationProperties时，将会把属性注入到返回结果的bean中
     */
    @Bean("qrtzDataSource")
    @ConfigurationProperties("spring.datasource.druid.qrtz")
    public DruidDataSource qrtzDataSource() throws SQLException {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Primary
    @Bean("dataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("bizDataSource") DataSource bizDataSource, @Qualifier("qrtzDataSource") DataSource qrtzDataSource) {
        DynamicDataSource bean = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.qrtzDataSource.getValue(), qrtzDataSource);
        targetDataSources.put(DBTypeEnum.bizDataSource.getValue(), bizDataSource);
        bean.setTargetDataSources(targetDataSources);
        bean.setDefaultTargetDataSource(qrtzDataSource);
        return bean;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource(bizDataSource(), qrtzDataSource()));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        // 配置mapper的扫描，找到所有的mapper.xml映射文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor()
        });
        sqlSessionFactory.setMapperLocations(resources);
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        conf.setIdType(0);
        //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        conf.setFieldStrategy(2);
        //自定义SQL注入器
        conf.setSqlInjector(new LogicSqlInjector());
        //配置填充字段
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        //驼峰下划线转换
        conf.setDbColumnUnderline(true);
        //刷新mapper 调试神器
        conf.setRefresh(true);
        //逻辑删除配置
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("0");
        return conf;
    }


}
