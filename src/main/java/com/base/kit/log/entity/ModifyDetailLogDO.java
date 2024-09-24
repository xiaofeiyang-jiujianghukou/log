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
 * 变更日志详情表
 * </p>
 *
 * @author xiaofeiyang
 * @since 2024-09-25
 */
@Getter
@Setter
@TableName("modify_detail_log")
public class ModifyDetailLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * customer_clue_operation_log日志ID
     */
    private Long logId;

    private String name;

    private String oldValue;

    private String newValue;

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
