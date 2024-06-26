package com.nwu.hospital.api.web;

import com.nwu.base.constant.ErrorMessages;
import com.nwu.base.jwt.JwtHelper;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.web.DoctorLoginDto;
import com.nwu.hospital.model.po.DoctorInfo;
import com.nwu.hospital.model.vo.DoctorLoginVo;
import com.nwu.hospital.service.IDoctorInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctorInfo/web")
@Tag(name = "web医生接口")
public class WebDoctorInfoController {
    @Autowired
    IDoctorInfoService iDoctorInfoService;

    @Operation(summary = "医生登录接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody DoctorLoginDto doctorLoginDto) {
        DoctorInfo doctorInfo = iDoctorInfoService.login(doctorLoginDto);
        if (doctorInfo == null)
            return Result.error(ErrorMessages.LOGIN_ERROR);
        String token = JwtHelper.generateToken(UserIdAndIdentity.builder().id(doctorInfo.getId()).identity(1).build());
        DoctorLoginVo doctorLoginVo = DoctorLoginVo.builder().id(doctorInfo.getId()).token(token).build();
        return Result.success(doctorLoginVo);
    }
}
