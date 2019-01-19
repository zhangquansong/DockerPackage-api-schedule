package com.clt.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
  *@ClassName : ScheduleJob
  *@Author : zhangquansong
  *@Date : 2019/1/19 0019 上午 11:32
  *@Description :定时任务
  **/
@Data
@Accessors(chain = true)
@TableName("schedule_job")
public class ScheduleJob extends Model<ScheduleJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;
    @TableField("bean_name")
    private String beanName;
    @TableField("method_name")
    private String methodName;
    private String params;
    @TableField("cron_expression")
    private String cronExpression;
    private Integer status;
    private String remark;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
    @TableField("is_delete")
    private Integer isDelete;
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.jobId;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "jobId=" + jobId +
                ", beanName=" + beanName +
                ", methodName=" + methodName +
                ", params=" + params +
                ", cronExpression=" + cronExpression +
                ", status=" + status +
                ", remark=" + remark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", version=" + version +
                "}";
    }
}
