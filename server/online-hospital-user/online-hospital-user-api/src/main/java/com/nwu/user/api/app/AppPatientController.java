package com.nwu.user.api.app;

import com.nwu.base.model.Result;
import com.nwu.user.model.dto.patient.AddPatientDto;
import com.nwu.user.model.dto.patient.UpdatePatientDto;
import com.nwu.user.model.po.PatientInfo;
import com.nwu.user.model.vo.patient.AppQueryAllPatientVo;
import com.nwu.user.model.vo.patient.AppQueryOnePatientVo;
import com.nwu.user.service.IPatientInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/23 22:30
 **/
@RestController
@Slf4j
@Api(tags = "App就诊人接口")
@RequestMapping("/app/patient")
public class AppPatientController {
    @Autowired
    IPatientInfoService iPatientInfoService;

    @ApiOperation(value = "添加就诊人")
    @PostMapping("/add")
    public Result addPatient(@RequestBody AddPatientDto addPatientDto) {
        iPatientInfoService.addPatient(addPatientDto);
        return Result.success();
    }

    @ApiOperation(value = "查询所有就诊人")
    @GetMapping("/queryAll")
    public Result<List<AppQueryAllPatientVo>> queryAllPatient() {
        List<PatientInfo> patientInfoList = iPatientInfoService.queryAllPatient();
        List<AppQueryAllPatientVo> appQueryAllPatientVoList = new ArrayList<>();
        for (int i = 0; i < patientInfoList.size(); i++) {
            PatientInfo patientInfo = patientInfoList.get(i);
            AppQueryAllPatientVo appQueryAllPatientVo = AppQueryAllPatientVo.builder().name(patientInfo.getName()).cardNo(patientInfo.getCardNo()).id(patientInfo.getId()).build();
            appQueryAllPatientVoList.add(appQueryAllPatientVo);

        }
        return Result.success(appQueryAllPatientVoList);
    }

    @ApiOperation(value = "查询单个就诊人")
    @GetMapping("/queryOne")
    public Result<AppQueryOnePatientVo> queryOnePatient(Long patientId) {
        PatientInfo patientInfo = iPatientInfoService.getById(patientId);
        AppQueryOnePatientVo appQueryOnePatientVo = AppQueryOnePatientVo.builder().build();
        BeanUtils.copyProperties(patientInfo, appQueryOnePatientVo);
        appQueryOnePatientVo.setAddressDetail(patientInfo.getProvinceName() + patientInfo.getCityName() + patientInfo.getDistrictName() + patientInfo.getAddress());
        return Result.success(appQueryOnePatientVo);
    }

    @ApiOperation(value = "修改就诊人")
    @PutMapping("/update")
    public Result updatePatient(@RequestBody UpdatePatientDto updatePatientDto) {
        iPatientInfoService.appUpdate(updatePatientDto);
        return Result.success();
    }

    @ApiOperation(value = "删除就诊人")
    @DeleteMapping("/delete")
    public Result deletePatient(Long patientId) {
        iPatientInfoService.appDelete(patientId);
        return Result.success();
    }

}
