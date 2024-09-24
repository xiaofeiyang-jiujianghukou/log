package com.base.kit.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 变更日志表
 * </p>
 *
 * @author xiaofeiyang
 * @since 2024-09-25
 */
@Getter
@Setter
@TableName("modify_log")
public class ModifyLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 业务类型
     */
    private Integer businessType;

    /**
     * 操作人编号
     */
    private String operator;

    /**
     * 操作姓名
     */
    private String operatorName;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 操作类行: 1-新增 2-编辑 3删除
     */
    private Byte operationType;

    /**
     * 是否有效
     */
    private Byte status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
