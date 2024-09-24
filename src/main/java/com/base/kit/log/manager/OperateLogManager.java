package com.base.kit.log.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.kit.log.common.BasePageResDTO;
import com.base.kit.log.controller.api.operateLog.dto.ListOperateLogReqDTO;
import com.base.kit.log.controller.api.operateLog.dto.ListOperateLogRespDTO;
import com.base.kit.log.controller.api.operateLog.dto.OperateDetailLogDTO;
import com.base.kit.log.controller.api.operateLog.dto.OperateLogCreateReqDTO;
import com.base.kit.log.entity.ModifyDetailLogDO;
import com.base.kit.log.entity.ModifyLogDO;
import com.base.kit.log.service.ModifyDetailLogService;
import com.base.kit.log.service.ModifyLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class OperateLogManager {

    @Autowired
    private ModifyLogService operateLogService;
    @Autowired
    private ModifyDetailLogService operateDetailLogService;

    @Async
    public void saveLog(ModifyLogDO ModifyLogDO, List<ModifyDetailLogDO> detailLogDOList) {
        if (Objects.isNull(ModifyLogDO) || CollectionUtils.isEmpty(detailLogDOList)) {
            return;
        }
        operateLogService.save(ModifyLogDO);

        for (ModifyDetailLogDO ModifyDetailLogDO : detailLogDOList) {
            ModifyDetailLogDO.setLogId(ModifyLogDO.getId());
        }
        operateDetailLogService.saveBatch(detailLogDOList);
    }


    public BasePageResDTO<ListOperateLogRespDTO> listOperateLog(ListOperateLogReqDTO input) {
        if (StringUtils.isNotBlank(input.getOperationStartTime()) && StringUtils.isNotBlank(input.getOperationEndTime())) {
            input.setOperationStartTime(input.getOperationStartTime() + " 00:00:00");
            input.setOperationEndTime(input.getOperationEndTime() + " 23:59:59");
        }
        log.info("listOperateLog input {}", input);
        Page<ModifyLogDO> page = operateLogService.lambdaQuery()
                .like(StringUtils.isNotBlank(input.getOperatorName()), ModifyLogDO::getOperatorName, input.getOperatorName())
                .between(StringUtils.isNotBlank(input.getOperationStartTime()) && StringUtils.isNotBlank(input.getOperationEndTime()), ModifyLogDO::getOperationTime, input.getOperationStartTime(), input.getOperationEndTime())
                .eq(Objects.nonNull(input.getBusinessType()), ModifyLogDO::getBusinessType, input.getBusinessType())
                .orderByDesc(ModifyLogDO::getOperationTime)
                .page(new Page<>(input.getPageIndex(), input.getPageSize()));

        BasePageResDTO<ListOperateLogRespDTO> pageResDTO = new BasePageResDTO<>();
        pageResDTO.setTotalCount(page.getTotal());
        if (CollectionUtils.isEmpty(page.getRecords())) {
            pageResDTO.setData(Collections.emptyList());
            return pageResDTO;
        }

        List<Long> logIds = new ArrayList<>();
        for (ModifyLogDO record : page.getRecords()) {
            logIds.add(record.getId());
        }

        List<ModifyDetailLogDO> detailLogDOS = operateDetailLogService.listByLogId(logIds);


        Map<Long, List<OperateDetailLogDTO>> detailMap = new HashMap<>();
        for (ModifyDetailLogDO detailLogDO : detailLogDOS) {
            List<OperateDetailLogDTO> detailList = detailMap.getOrDefault(detailLogDO.getLogId(), new ArrayList<>());
            detailList.add(OperateDetailLogDTO.builder()
                    .name(detailLogDO.getName())
                    .oldValue(detailLogDO.getOldValue())
                    .newValue(detailLogDO.getNewValue())
                    .build());
            detailMap.put(detailLogDO.getLogId(), detailList);
        }

        List<ListOperateLogRespDTO> respDTOS = new ArrayList<>();
        for (ModifyLogDO record : page.getRecords()) {
            List<OperateDetailLogDTO> detailList = detailMap.get(record.getId());
            respDTOS.add(ListOperateLogRespDTO.builder()
                    .id(record.getId())
                    .businessName(record.getBusinessName())
                    .operatorName(record.getOperatorName())
                    .operationTime(record.getOperationTime())
                    .detailList(detailList)
                    .build());
        }

        pageResDTO.setData(respDTOS);
        return pageResDTO;
    }

    public void operateLogCreate(OperateLogCreateReqDTO input) {
        if (CollectionUtils.isEmpty(input.getDetailList())) {
            return;
        }
        ModifyLogDO ModifyLogDO = new ModifyLogDO();
        ModifyLogDO.setBusinessId(input.getBusinessId());
        ModifyLogDO.setBusinessName(input.getBusinessName());
        ModifyLogDO.setBusinessType(input.getBusinessType());
        ModifyLogDO.setOperator(input.getOperator());
        ModifyLogDO.setOperatorName(input.getOperatorName());
        ModifyLogDO.setOperationTime(input.getOperationTime());
        operateLogService.save(ModifyLogDO);
        List<ModifyDetailLogDO> ModifyDetailLogDOS = new ArrayList<>();
        for (OperateDetailLogDTO operateDetailLogDTO : input.getDetailList()) {
            ModifyDetailLogDO ModifyDetailLogDO = new ModifyDetailLogDO();
            ModifyDetailLogDO.setLogId(ModifyLogDO.getId());
            ModifyDetailLogDO.setName(operateDetailLogDTO.getName());
            ModifyDetailLogDO.setOldValue(operateDetailLogDTO.getOldValue());
            ModifyDetailLogDO.setNewValue(operateDetailLogDTO.getNewValue());
            ModifyDetailLogDOS.add(ModifyDetailLogDO);
        }

        operateDetailLogService.saveBatch(ModifyDetailLogDOS);
    }
}
