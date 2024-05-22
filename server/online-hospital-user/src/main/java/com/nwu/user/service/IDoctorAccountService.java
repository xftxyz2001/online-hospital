package com.nwu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.user.model.dto.doctoraccount.DoctorAccountLoginDto;
import com.nwu.user.model.po.DoctorAccount;

/**
 * <p>
 * 医生账户 服务类
 * </p>
 *
 * @author author
 * @since 2024-04-01
 */
public interface IDoctorAccountService extends IService<DoctorAccount> {

    DoctorAccount login(DoctorAccountLoginDto doctorAccountLoginDto);
}
