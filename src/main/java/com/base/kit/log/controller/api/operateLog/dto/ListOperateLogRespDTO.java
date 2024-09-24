package com.base.kit.log.controller.api.operateLog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListOperateLogRespDTO {

    @Schema(name = "日志ID")
    private Long id;

    @Schema(name = "业务名称")
    private String businessName;

    @Schema(name = "操作人姓名")
    private String operatorName;

    @Schema(name = "操作时间")
    @JsonFormat(locale="zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime operationTime;

    @Schema(name = "明细")
    private List<OperateDetailLogDTO> detailList;
}
