package com.nwu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.user.model.dto.doctoraccount.DoctorAccountLoginDto;
import com.nwu.user.model.po.DoctorAccount;

public interface IDoctorAccountService extends IService<DoctorAccount> {

    DoctorAccount login(DoctorAccountLoginDto doctorAccountLoginDto);
}
