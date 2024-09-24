package com.base.kit.log.controller.api.operateLog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperateDetailLogDTO {
    @Schema(name = "业务名称")
    private String name;
    @Schema(name = "旧值")
    private String oldValue;
    @Schema(name = "新值")
    private String newValue;
}
