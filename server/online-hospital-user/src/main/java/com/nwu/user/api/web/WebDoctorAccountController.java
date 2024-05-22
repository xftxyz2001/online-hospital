package com.nwu.user.api.web;

import com.nwu.base.constant.ErrorMessages;
import com.nwu.base.jwt.JwtHelper;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.doctoraccount.DoctorAccountLoginDto;
import com.nwu.user.model.po.DoctorAccount;
import com.nwu.user.model.vo.doctoraccount.DoctorLoginVo;
import com.nwu.user.service.IDoctorAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 医生账户 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-04-01
 */
@RestController
@Tag(name = "web医生账户接口")
@RequestMapping("web/doctor-account")
public class WebDoctorAccountController {
    @Autowired
    IDoctorAccountService iDoctorAccountService;

    @Operation(summary = "医生登录接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody DoctorAccountLoginDto doctorAccountLoginDto) {
        DoctorAccount doctorAccount = iDoctorAccountService.login(doctorAccountLoginDto);
        if (doctorAccount == null)
            return Result.error(ErrorMessages.LOGIN_ERROR);
        String token = JwtHelper
                .generateToken(UserIdAndIdentity.builder().id(doctorAccount.getId()).identity(1).build());
        DoctorLoginVo doctorLoginVo = DoctorLoginVo.builder().id(doctorAccount.getId()).token(token).build();
        return Result.success(doctorLoginVo);
    }

}
