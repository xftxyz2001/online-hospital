package com.nwu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryUserInfoDto;
import com.nwu.user.model.dto.UpdateUserInfoDto;
import com.nwu.user.model.dto.user.AppQueryUserInfoVo;
import com.nwu.user.model.dto.user.AppUpdateUserInfoDto;
import com.nwu.user.model.po.UserInfo;


public interface IUserInfoService extends IService<UserInfo> {

    Result<PageResult<UserInfo>> queryUserInfoList(PageParams pageParams, QueryUserInfoDto queryUserInfoDto);

    Result<?> deleteUserById(Long id);

    Result<?> enableUserById(Long id);

    Result<?> disenableUserById(Long id);

    Result<?> getUserInfoById(Long id);

    Result<?> updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    UserInfo appUserLogin(String openid);

    UserInfo isFirstLogin(String openid);

    String getUsername();

    void appUpdateUserInfo(AppUpdateUserInfoDto appUpdateUserInfoDto);

    AppQueryUserInfoVo appQueryUserInfo();
}
