package com.nwu.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.user.mapper.DoctorAccountMapper;
import com.nwu.user.model.dto.doctoraccount.DoctorAccountLoginDto;
import com.nwu.user.model.po.DoctorAccount;
import com.nwu.user.service.IDoctorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生账户 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-04-01
 */
@Service
public class DoctorAccountServiceImpl extends ServiceImpl<DoctorAccountMapper, DoctorAccount> implements IDoctorAccountService {

    @Autowired
    DoctorAccountMapper doctorAccountMapper;

    @Override
    public DoctorAccount login(DoctorAccountLoginDto doctorAccountLoginDto) {
        LambdaQueryWrapper<DoctorAccount> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DoctorAccount::getUsername, doctorAccountLoginDto.getUsername());
        lambdaQueryWrapper.eq(DoctorAccount::getPassword, doctorAccountLoginDto.getPassword());
        DoctorAccount doctorAccount = doctorAccountMapper.selectOne(lambdaQueryWrapper);

        return doctorAccount;

    }
}
