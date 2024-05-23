package com.nwu.user.api;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryPatientInfoDto;
import com.nwu.user.model.dto.UpdatePatientInfoDto;
import com.nwu.user.model.po.PatientInfo;
import com.nwu.user.service.IPatientInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "就诊人接口")
@RequestMapping("/patient")
public class PatientInfoController {
    @Autowired
    IPatientInfoService iPatientInfoService;

    @Operation(summary = "根据用户id查询就诊人")
    @GetMapping("/listByUserId")
    public Result<PageResult<PatientInfo>> getPatientListByUserId(Long userId, PageParams pageParams) {
        return iPatientInfoService.getPatientListByUserId(userId, pageParams);
    }

    @Operation(summary = "删除就诊人")
    @DeleteMapping("")
    public Result<?> deletePatientById(Long id) {
        return iPatientInfoService.deletePatientById(id);
    }

    @Operation(summary = "查询单条就诊人信息")
    @GetMapping("")
    public Result<?> getPatientInfoById(Long id) {
        return iPatientInfoService.getPatientInfoById(id);
    }

    @PutMapping("")
    @Operation(summary = "修改就诊人")
    public Result<?> updatePatientInfo(@RequestBody(required = false) UpdatePatientInfoDto updatePatientInfoDto) {

        return iPatientInfoService.updatePatientInfo(updatePatientInfoDto);
    }

    @PostMapping("/list")
    @Operation(summary = "就诊人查询接口")
    public Result<PageResult<PatientInfo>> list(PageParams pageParams, @RequestBody(required = false) QueryPatientInfoDto queryPatientInfoDto) {

        return iPatientInfoService.queryPatientInfoList(pageParams, queryPatientInfoDto);
    }


}
