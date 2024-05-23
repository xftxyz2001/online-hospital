package com.nwu.user.api;

import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryUserInfoDto;
import com.nwu.user.model.dto.UpdateUserInfoDto;
import com.nwu.user.model.po.UserInfo;
import com.nwu.user.service.IUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "用户管理接口")
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    IUserInfoService iUserInfoService;

    @PostMapping("/list")
    @Operation(summary = "用户查询接口")
    public Result<PageResult<UserInfo>> list(PageParams pageParams, @RequestBody(required = false) QueryUserInfoDto queryUserInfoDto) {

        return iUserInfoService.queryUserInfoList(pageParams, queryUserInfoDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "逻辑删除用户接口")
    public Result<?> deleteUserById(@PathVariable Long id) {
        return iUserInfoService.deleteUserById(id);
        //return iUserInfoService.removeById(id)?Result.success():Result.error(DELETE_FAILED);
    }

    @PutMapping("/enable/{id}")
    @Operation(summary = "启用用户接口")
    public Result<?> enableUserById(@PathVariable Long id) {
        return iUserInfoService.enableUserById(id);
    }

    @PutMapping("/disenable/{id}")
    @Operation(summary = "禁用用户接口")
    public Result<?> disenableUserById(@PathVariable Long id) {
        return iUserInfoService.disenableUserById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询单条用户")
    public Result<?> getUserInfoById(@PathVariable Long id) {
        return iUserInfoService.getUserInfoById(id);
    }

    @PutMapping("")
    @Operation(summary = "修改用户")
    public Result<?> updateUserInfo(@RequestBody(required = false) UpdateUserInfoDto updateUserInfoDto) {

        return iUserInfoService.updateUserInfo(updateUserInfoDto);
    }


}
