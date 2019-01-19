package com.clt.api.common.dataSource;

/**
  *@ClassName : DbContextHolder
  *@Author : zhangquansong
  *@Date : 2019/1/19 0019 上午 11:29
  *@Description :DbContextHolder
  **/
public class DbContextHolder {
    private static final ThreadLocal contextHolder = new ThreadLocal<>();
    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDbType() {
        return (String) contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
