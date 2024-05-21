package com.nwu.system.api;


import com.nwu.base.context.BaseContext;
import com.nwu.base.model.Result;
import com.nwu.system.model.dto.ManagerLoginDto;
import com.nwu.system.model.po.Manager;
import com.nwu.system.model.vo.ManagerQueryUsernameVo;
import com.nwu.system.service.IManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-13
 */
@RestController
@Slf4j
@Api(tags = "管理员接口")
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    IManagerService iManagerService;

    @ApiOperation("管理员登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody ManagerLoginDto managerLoginDto) {
        return iManagerService.login(managerLoginDto);
    }

    @ApiOperation("管理员名称查询")
    @GetMapping("/queryUsername")
    public Result<ManagerQueryUsernameVo> queryUsername() {

        Manager manager = iManagerService.getById(BaseContext.getCurrentId());
        ManagerQueryUsernameVo managerQueryUsernameVo = new ManagerQueryUsernameVo();
        managerQueryUsernameVo.setUsername(manager.getUsername());
        return Result.success(managerQueryUsernameVo);
    }
}
