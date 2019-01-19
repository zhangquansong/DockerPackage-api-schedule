package com.clt.api.common.dataSource;

/**
 * @ClassName : DBTypeEnum
 * @Author : zhangquansong
 * @Date : 2019/1/19 0019 上午 11:29
 * @Description :动态数据库类型
 **/
public enum DBTypeEnum {
    biz("biz"), qrtz("qrtz");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
