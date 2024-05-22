package com.nwu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.constant.MessageConstant;
import com.nwu.base.jwt.JwtHelper;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.base.model.Result;
import com.nwu.system.mapper.ManagerMapper;
import com.nwu.system.model.dto.ManagerLoginDto;
import com.nwu.system.model.po.Manager;
import com.nwu.system.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-13
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
    @Autowired
    ManagerMapper managerMapper;

    @Override
    public Result<?> login(ManagerLoginDto managerLoginDto) {
        LambdaQueryWrapper<Manager> managerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        managerLambdaQueryWrapper.eq(Manager::getUsername, managerLoginDto.getUsername());
        managerLambdaQueryWrapper.eq(Manager::getPassword, managerLoginDto.getPassword());
        Manager manager = managerMapper.selectOne(managerLambdaQueryWrapper);
        if (manager == null) {
            return Result.error(MessageConstant.LOGIN_FAILED);
        }
        String jwt = JwtHelper.generateToken(UserIdAndIdentity.builder().id(manager.getId().longValue()).identity(2).build());
        return Result.success(jwt);
    }
}
