package com.clt.api.datasources.aspect;

import com.clt.api.datasources.DBTypeEnum;
import com.clt.api.datasources.DbContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 拦截Dao层进行切换
 *
 * @author Administrator
 */
@Aspect
@Component
public class DataSourceInterceptor {
    Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);

    @Pointcut(value = "execution(public * com.clt.api.mapper..**.*(..))")
    private void bizDataSourceServicePointcut() {

    }

    ;

    @Pointcut(value = "execution(public * com.clt.api.mapper.**.*(..))")
    private void qrtzDataSourceServicePointcut() {

    }

    ;

    /**
     * 切换数据源1
     */
    @Before("bizDataSourceServicePointcut()")
    public void bizDataSourceInterceptor() {
        logger.info("切换到数据源{}.......................", "bizDataSource");
        DbContextHolder.setDbType(DBTypeEnum.bizDataSource);
    }

    /**
     * 切换数据源2
     */
    @Before("qrtzDataSourceServicePointcut()")
    public void qrtzDataSourceInterceptor() {
        logger.info("切换到数据源{}.......................", "qrtzDataSource");
        DbContextHolder.setDbType(DBTypeEnum.qrtzDataSource);
    }
}
