package com.clt.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

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
@TableName("schedule_job_log")
public class ScheduleJobLog extends Model<ScheduleJobLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="log_id", type= IdType.AUTO)
	private Long logId;
	@TableField("job_id")
	private Long jobId;
	@TableField("bean_name")
	private String beanName;
	@TableField("method_name")
	private String methodName;
	private String params;
	private Integer status;
	private String error;
	private Integer times;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("is_delete")
	private Integer isDelete;
	private Integer version;


	@Override
	protected Serializable pkVal() {
		return this.logId;
	}

	@Override
	public String toString() {
		return "ScheduleJobLog{" +
			"logId=" + logId +
			", jobId=" + jobId +
			", beanName=" + beanName +
			", methodName=" + methodName +
			", params=" + params +
			", status=" + status +
			", error=" + error +
			", times=" + times +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", isDelete=" + isDelete +
			", version=" + version +
			"}";
	}
}
