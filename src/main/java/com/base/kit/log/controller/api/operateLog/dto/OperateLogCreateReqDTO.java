package com.base.kit.log.controller.api.operateLog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperateLogCreateReqDTO {

    @Schema(name = "业务ID")
    @NotBlank(message = "业务ID 不能为空")
    private String businessId;

    @Schema(name = "业务名称")
    @NotBlank(message = "业务名称 不能为空")
    private String businessName;

    @Schema(name = "业务类型")
    @NotBlank(message = "业务类型 不能为空")
    private Integer businessType;

    @Schema(name = "操作人编号")
    @NotBlank(message = "操作人编号 不能为空")
    private String operator;

    @Schema(name = "操作人姓名")
    @NotBlank(message = "操作人姓名 不能为空")
    private String operatorName;

    @Schema(name = "操作时间")
    @JsonFormat(locale="zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "操作时间 不能为空")
    private LocalDateTime operationTime;

    @Schema(name = "明细")
    @NotEmpty(message = "明细 不能为空")
    private List<OperateDetailLogDTO> detailList;
}
