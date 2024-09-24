package com.base.kit.log.controller.api.operateLog.dto;

import com.base.kit.log.common.BasePageReqDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListOperateLogReqDTO extends BasePageReqDTO {
    @Schema(name = "业务类型")
    private Integer businessType;

    @Schema(name = "操作人姓名")
    private String operatorName;
    @Schema(name = "操作开始时间")
    private String operationStartTime;
    @Schema(name = "操作结束时间")
    private String operationEndTime;
}
