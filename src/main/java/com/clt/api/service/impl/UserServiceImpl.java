package com.clt.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.common.utils.CheckUtils;
import com.clt.api.common.utils.Constants;
import com.clt.api.entity.User;
import com.clt.api.mapper.biz.UserMapper;
import com.clt.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : UserServiceImpl
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:24
 * @Description :用户基本CURD操作实现类
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * 新增
     *
     * @param user
     */
    @Override
    public void create(User user) {
        this.insertOrUpdate(user);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @Override
    public void delete(Integer id) {
        this.deleteById(id);
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public void edit(User user) {
        this.updateById(user);
    }

    /**
     * @param user
     * @param version
     * @return void
     * @Author zhangquansong
     * @Date 2019/1/18 0018 上午 11:32
     * @Description : 修改用户信息带版本条件
     **/
//    @DataSourceSwitch(DBTypeEnum.biz)
    @Override
    public void editUserWithVersion(User user, Long version) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.and("user_version={0}", version).and("id={0}", user.getId());
        this.update(user, userEntityWrapper);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<User> listAll() {
        return this.selectList(null);
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return
     */
//    @DataSourceSwitch(DBTypeEnum.biz)
    @Override
    public User findById(Integer id) {
        return this.selectById(id);
    }


    /**
     * 根据登录名和密码查询可用用户信息
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    @Override
    public User findUserByLoginNameAndPassword(String loginName, String password) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.and("user_login_name={0}", loginName)
                .and("user_password={0}", password)
                .and("is_delete={0}", Constants.IS_DELETE_0)//未删除状态
                .and("user_status={0}", Constants.USER_STATUS_0);//启用状态
        List<User> userList = this.selectList(userEntityWrapper);
        if (CheckUtils.isNotEmpty(userList)) {
            return userList.get(Constants.INTEGER_VALUE_0);
        } else {
            return null;
        }
    }

    /**
     * 根据用户id查询可用用户信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public User findUserByUserId(Long userId) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.and("id={0}", userId)
                .and("is_delete={0}", Constants.IS_DELETE_0)//未删除状态
                .and("user_status={0}", Constants.USER_STATUS_0);//启用状态
        List<User> userList = this.selectList(userEntityWrapper);
        if (CheckUtils.isNotEmpty(userList)) {
            return userList.get(Constants.INTEGER_VALUE_0);
        } else {
            return null;
        }
    }
}