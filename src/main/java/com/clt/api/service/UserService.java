package com.clt.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.clt.api.entity.User;

import java.util.List;

/**
 * @ClassName : UserService
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:25
 * @Description :用户基本操作CURD接口
 **/
public interface UserService extends IService<User> {

    /**
     * 新增
     *
     * @param user
     */
    void create(User user);

    /**
     * 删除
     *
     * @param id 主键id
     */
    void delete(Integer id);

    /**
     * 修改
     *
     * @param user
     */
    void edit(User user);

    /**
     * @param user
     * @param version
     * @return void
     * @Author zhangquansong
     * @Date 2019/1/18 0018 上午 11:32
     * @Description : 修改用户信息带版本条件
     **/
    void editUserWithVersion(User user, Long version);

    /**
     * 列表(全部)
     *
     * @return
     */
    List<User> listAll();

    /**
     * 通过id获取数据
     *
     * @param id 主键id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据登录名和密码查询可用用户信息
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    User findUserByLoginNameAndPassword(String loginName, String password);


    /**
     * 根据用户id查询可用用户信息
     *
     * @param userId 用户id
     * @return
     */
    User findUserByUserId(Long userId);
}