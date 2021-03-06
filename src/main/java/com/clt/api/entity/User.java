package com.clt.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName : User
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:20
 * @Description :用户信息表
 **/
@Data
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_login_name")
    private String userLoginName;
    @TableField("user_name")
    private String userName;
    @TableField("user_phone")
    private String userPhone;
    @TableField("user_sex")
    private Integer userSex;
    @TableField("user_id_card")
    private String userIdCard;
    @TableField("user_face")
    private String userFace;
    @TableField("user_code")
    private String userCode;
    @TableField("user_type")
    private Integer userType;
    @TableField("user_password")
    private String userPassword;
    @TableField("user_asset")
    private BigDecimal userAsset;
    @TableField("user_address")
    private String userAddress;
    @TableField("user_longin_last_time")
    private Date userLonginLastTime;
    @TableField("user_status")
    private Integer userStatus;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("user_version")
    private Long userVersion;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userLoginName=" + userLoginName +
                ", userName=" + userName +
                ", userPhone=" + userPhone +
                ", userSex=" + userSex +
                ", userIdCard=" + userIdCard +
                ", userFace=" + userFace +
                ", userCode=" + userCode +
                ", userType=" + userType +
                ", userPassword=" + userPassword +
                ", userAsset=" + userAsset +
                ", userAddress=" + userAddress +
                ", userLonginLastTime=" + userLonginLastTime +
                ", userStatus=" + userStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userVersion=" + userVersion +
                "}";
    }
}
