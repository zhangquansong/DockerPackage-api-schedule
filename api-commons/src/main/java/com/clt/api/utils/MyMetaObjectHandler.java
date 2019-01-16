package com.clt.api.utils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置fill = FieldFill.INSERT字段！
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入填充createTime字段:{}", new Object[]{new Date()});
        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新填充modifyTime字段:{}", new Object[]{new Date()});
        setFieldValByName("modifyTime", new Date(), metaObject);
    }

}
