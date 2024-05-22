package com.nwu.user.api;

import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryUserInfoDto;
import com.nwu.user.model.dto.UpdateUserInfoDto;
import com.nwu.user.model.po.UserInfo;
import com.nwu.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/1 15:39
 **/
@RestController
@Slf4j
@Api(tags = "用户管理接口")
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    IUserInfoService iUserInfoService;

    @PostMapping("/list")
    @ApiOperation("用户查询接口")
    public Result<PageResult<UserInfo>> list(PageParams pageParams, @RequestBody(required = false) QueryUserInfoDto queryUserInfoDto) {

        return iUserInfoService.queryUserInfoList(pageParams, queryUserInfoDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除用户接口")
    public Result deleteUserById(@PathVariable Long id) {
        return iUserInfoService.deleteUserById(id);
        //return iUserInfoService.removeById(id)?Result.success():Result.error(DELETE_FAILED);
    }

    @PutMapping("/enable/{id}")
    @ApiOperation("启用用户接口")
    public Result enableUserById(@PathVariable Long id) {
        return iUserInfoService.enableUserById(id);
    }

    @PutMapping("/disenable/{id}")
    @ApiOperation("禁用用户接口")
    public Result disenableUserById(@PathVariable Long id) {
        return iUserInfoService.disenableUserById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询单条用户")
    public Result<UserInfo> getUserInfoById(@PathVariable Long id) {
        return iUserInfoService.getUserInfoById(id);
    }

    @PutMapping("")
    @ApiOperation("修改用户")
    public Result updateUserInfo(@RequestBody(required = false) UpdateUserInfoDto updateUserInfoDto) {

        return iUserInfoService.updateUserInfo(updateUserInfoDto);
    }


}
