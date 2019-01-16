package com.clt.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangquansong
 * @since 2019-01-14
 */
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
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
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
