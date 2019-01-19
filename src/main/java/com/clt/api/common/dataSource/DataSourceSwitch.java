package com.clt.api.common.dataSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
  *@ClassName : DataSourceSwitch
  *@Author : zhangquansong
  *@Date : 2019/1/19 0019 上午 11:28
  *@Description :动态数据库类型接口
  **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSourceSwitch {
    DBTypeEnum value() default DBTypeEnum.qrtz;
}
