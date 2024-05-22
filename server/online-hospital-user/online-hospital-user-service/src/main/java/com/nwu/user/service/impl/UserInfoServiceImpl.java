package com.nwu.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.constant.MessageConstant;
import com.nwu.base.constant.UserConstant;
import com.nwu.base.context.BaseContext;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.mapper.UserInfoMapper;
import com.nwu.user.model.dto.QueryUserInfoDto;
import com.nwu.user.model.dto.UpdateUserInfoDto;
import com.nwu.user.model.dto.user.AppQueryUserInfoVo;
import com.nwu.user.model.dto.user.AppUpdateUserInfoDto;
import com.nwu.user.model.po.UserInfo;
import com.nwu.user.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-05
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public Result<PageResult<UserInfo>> queryUserInfoList(PageParams pageParams, QueryUserInfoDto queryUserInfoDto) {

        Page<UserInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper.like(StringUtils.isNotEmpty(queryUserInfoDto.getUserName()), UserInfo::getUsername, queryUserInfoDto.getUserName())
                .like(StringUtils.isNotEmpty(queryUserInfoDto.getPhone()), UserInfo::getPhone, queryUserInfoDto.getPhone())
                .eq(StringUtils.isNotEmpty(queryUserInfoDto.getStatus()), UserInfo::getStatus, queryUserInfoDto.getStatus())
                .eq(UserInfo::getIsDeleted, UserConstant.NOT_DELETED);
        Page<UserInfo> userInfoPage = userInfoMapper.selectPage(page, userInfoLambdaQueryWrapper);
        List<UserInfo> userInfoList = userInfoPage.getRecords();
        Long counts = userInfoPage.getTotal();
        PageResult<UserInfo> userInfoPageResult = new PageResult<>(userInfoList, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(userInfoPageResult);
    }

    @Override
    public Result<?> deleteUserById(Long id) {
        LambdaUpdateWrapper<UserInfo> userInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userInfoLambdaUpdateWrapper.eq(UserInfo::getId, id).set(UserInfo::getIsDeleted, UserConstant.IS_DELETED);
        userInfoMapper.update(null, userInfoLambdaUpdateWrapper);
        return Result.success();
    }

    @Override
    public Result<?> enableUserById(Long id) {
        LambdaUpdateWrapper<UserInfo> userInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userInfoLambdaUpdateWrapper.eq(UserInfo::getId, id).set(UserInfo::getStatus, UserConstant.ENABLE);
        userInfoMapper.update(null, userInfoLambdaUpdateWrapper);
        return Result.success();
    }

    @Override
    public Result<?> disenableUserById(Long id) {
        LambdaUpdateWrapper<UserInfo> userInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userInfoLambdaUpdateWrapper.eq(UserInfo::getId, id).set(UserInfo::getStatus, UserConstant.DISENABLE);
        userInfoMapper.update(null, userInfoLambdaUpdateWrapper);
        return Result.success();
    }

    @Override
    public Result<?> getUserInfoById(Long id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        return userInfo == null ? Result.error(MessageConstant.QUERY_FAILED) : Result.success(userInfo);
    }

    @Override
    public Result<?> updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        updateUserInfoDto.setModifiedTime(LocalDateTime.now());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(updateUserInfoDto, userInfo);
        userInfoMapper.updateById(userInfo);
        return Result.success();
    }


    //小程序端用户Service

    @Override
    public UserInfo appUserLogin(String openid) {
        //新建用户
        UserInfo newUser = new UserInfo();
        newUser.setCreateTime(LocalDateTime.now())
                .setModifiedTime(LocalDateTime.now())
                .setOpenId(openid)
                .setStatus(UserConstant.ENABLE)
                .setIsDeleted(UserConstant.NOT_DELETED)
                .setUsername("微信用户")
                .setAvatar("https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0");
        userInfoMapper.insert(newUser);
        return newUser;
    }

    @Override
    public UserInfo isFirstLogin(String openid) {
        //查询是否存在用户
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getOpenId, openid);
        UserInfo userInfo = userInfoMapper.selectOne(lambdaQueryWrapper);
        if (userInfo != null) {
            //已有用户
            return userInfo;
        }
        return null;
    }

    @Override
    public String getUsername() {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getId, BaseContext.getCurrentId());
        UserInfo userInfo = userInfoMapper.selectOne(lambdaQueryWrapper);
        return userInfo.getUsername();
    }

    @Override
    public void appUpdateUserInfo(AppUpdateUserInfoDto appUpdateUserInfoDto) {
        LambdaUpdateWrapper<UserInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(UserInfo::getId, BaseContext.getCurrentId())
                .set(StringUtils.isNotEmpty(appUpdateUserInfoDto.getAvatar()), UserInfo::getAvatar, appUpdateUserInfoDto.getAvatar())
                .set(StringUtils.isNotEmpty(appUpdateUserInfoDto.getUsername()), UserInfo::getUsername, appUpdateUserInfoDto.getUsername())
                .set(StringUtils.isNotEmpty(appUpdateUserInfoDto.getPhone()), UserInfo::getPhone, appUpdateUserInfoDto.getPhone());
        userInfoMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public AppQueryUserInfoVo appQueryUserInfo() {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getId, BaseContext.getCurrentId());
        UserInfo userInfo = userInfoMapper.selectOne(lambdaQueryWrapper);
        AppQueryUserInfoVo appQueryUserInfoVo = new AppQueryUserInfoVo();
        BeanUtils.copyProperties(userInfo, appQueryUserInfoVo);
        return appQueryUserInfoVo;
    }
}
