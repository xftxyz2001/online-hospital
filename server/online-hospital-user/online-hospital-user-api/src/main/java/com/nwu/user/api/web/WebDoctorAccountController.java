package com.nwu.user.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nwu.base.model.Result;
import com.nwu.base.utils.JwtHelper;
import com.nwu.user.model.dto.doctoraccount.DoctorAccountLoginDto;
import com.nwu.user.model.po.DoctorAccount;
import com.nwu.user.model.vo.doctoraccount.DoctorLoginVo;
import com.nwu.user.service.IDoctorAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 医生账户 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-04-01
 */
@RestController
@Api(tags = "web医生账户接口")
@RequestMapping("web/doctor-account")
public class WebDoctorAccountController {
    @Autowired
    IDoctorAccountService iDoctorAccountService;

    @ApiOperation("医生登录接口")
    @PostMapping("/login")
    public Result<DoctorLoginVo> login(@RequestBody DoctorAccountLoginDto doctorAccountLoginDto) {
        DoctorAccount doctorAccount = iDoctorAccountService.login(doctorAccountLoginDto);
        if (doctorAccount == null)
            return Result.error("登陆失败");
        String token = JwtHelper
                .generateToken(JwtHelper.UserInfo.builder().id(doctorAccount.getId()).identity(1).build());
        DoctorLoginVo doctorLoginVo = DoctorLoginVo.builder().id(doctorAccount.getId()).token(token).build();
        return Result.success(doctorLoginVo);
    }

}
