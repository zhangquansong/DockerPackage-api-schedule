package com.clt.api.common.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName : DataSourceSwitchAspect
 * @Author : zhangquansong
 * @Date : 2019/1/19 0019 上午 11:29
 * @Description :动态数据库aop
 **/
@Component
@Aspect
@Order(-100)
@Slf4j
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.clt.api.mapper.qrtz..*.*(..))")
    private void qrtzAspect() {
    }

    @Pointcut("execution(* com.clt.api.mapper.biz..*.*(..))")
    private void bizAspect() {
    }

    @Before("qrtzAspect()")
    public void qrtz(JoinPoint joinPoint) {
        log.info("切换到qrtz 数据源...");
        setDataSource(joinPoint, DBTypeEnum.qrtz);
    }

    @Before("bizAspect()")
    public void biz(JoinPoint joinPoint) {
        log.info("切换到biz 数据源...");
        setDataSource(joinPoint, DBTypeEnum.biz);
    }

    /**
     * 添加注解方式,如果有注解优先注解,没有则按传过来的数据源配置
     *
     * @param joinPoint
     * @param dbTypeEnum
     */
    private void setDataSource(JoinPoint joinPoint, DBTypeEnum dbTypeEnum) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DataSourceSwitch dataSourceSwitch = methodSignature.getMethod().getAnnotation(DataSourceSwitch.class);
        if (Objects.isNull(dataSourceSwitch) || Objects.isNull(dataSourceSwitch.value())) {
            DbContextHolder.setDbType(dbTypeEnum);
        } else {
            log.info("根据注解来切换数据源,注解值为:" + dataSourceSwitch.value());
            switch (dataSourceSwitch.value().getValue()) {
                case "qrtz":
                    DbContextHolder.setDbType(DBTypeEnum.qrtz);
                    break;
                case "biz":
                    DbContextHolder.setDbType(DBTypeEnum.biz);
                    break;
                default:
                    DbContextHolder.setDbType(dbTypeEnum);
            }
        }
    }
}
