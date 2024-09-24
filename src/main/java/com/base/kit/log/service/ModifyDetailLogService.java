package com.base.kit.log.service;

import com.base.kit.log.common.enums.StatusEnum;
import com.base.kit.log.entity.ModifyDetailLogDO;
import com.base.kit.log.mapper.ModifyDetailLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 变更日志详情表 服务实现类
 * </p>
 *
 * @author xiaofeiyang
 * @since 2024-09-25
 */
@Service
public class ModifyDetailLogService extends ServiceImpl<ModifyDetailLogMapper, ModifyDetailLogDO> {

    public List<ModifyDetailLogDO> listByLogId(List<Long> logIds) {
        if (CollectionUtils.isEmpty(logIds)) {
            return Collections.emptyList();
        }
        return this.lambdaQuery()
                .in(ModifyDetailLogDO::getLogId, logIds)
                .eq(ModifyDetailLogDO::getStatus, StatusEnum.NOT.getValue())
                .list();
    }
}
