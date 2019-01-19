package com.clt.api.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * @ClassName : MyMetaObjectHandler
 * @Author : zhangquansong
 * @Date : 2019/1/19 0019 上午 11:30
 * @Description :MetaObjectHandler
 **/
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置fill = FieldFill.INSERT字段！
     */
    public void insertFill(MetaObject metaObject) {
        // 更多查看源码测试用例
        log.info("*************************");
        log.info("insert fill");
        log.info("*************************");

        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (createTime == null) {
            setFieldValByName("createTime", timestamp, metaObject);//mybatis-plus版本2.0.9+
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新填充
        log.info("*************************");
        log.info("update fill");
        log.info("*************************");
        //mybatis-plus版本2.0.9+
        setFieldValByName("modifyTime", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}
