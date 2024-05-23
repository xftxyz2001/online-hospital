package com.nwu.system.api;


import com.nwu.base.context.BaseContext;
import com.nwu.base.model.Result;
import com.nwu.system.model.dto.ManagerLoginDto;
import com.nwu.system.model.po.Manager;
import com.nwu.system.model.vo.ManagerQueryUsernameVo;
import com.nwu.system.service.IManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "管理员接口")
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    IManagerService iManagerService;

    @Operation(summary = "管理员登录接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody ManagerLoginDto managerLoginDto) {
        return iManagerService.login(managerLoginDto);
    }

    @Operation(summary = "管理员名称查询")
    @GetMapping("/queryUsername")
    public Result<ManagerQueryUsernameVo> queryUsername() {

        Manager manager = iManagerService.getById(BaseContext.getUserIdentity().getId());
        ManagerQueryUsernameVo managerQueryUsernameVo = new ManagerQueryUsernameVo();
        managerQueryUsernameVo.setUsername(manager.getUsername());
        return Result.success(managerQueryUsernameVo);
    }
}
