package com.base.kit.log.controller.api.operateLog;


import com.base.kit.log.common.BasePageResDTO;
import com.base.kit.log.common.Result;
import com.base.kit.log.controller.api.operateLog.dto.ListOperateLogReqDTO;
import com.base.kit.log.controller.api.operateLog.dto.ListOperateLogRespDTO;
import com.base.kit.log.manager.OperateLogManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modifyLog")
@Tag(name = "变更日志管理", description = "包含用户相关的操作")
public class ModifyLogAPIController {

    @Resource
    private OperateLogManager operateLogManager;

    @PostMapping("listOperateLog")
    @Operation(summary = "获取用户信息", description = "通过用户ID获取用户的详细信息")
    public Result<BasePageResDTO<ListOperateLogRespDTO>> listOperateLog(@RequestBody ListOperateLogReqDTO input) {
        return Result.success(operateLogManager.listOperateLog(input));
    }

}
