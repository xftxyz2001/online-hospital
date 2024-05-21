package com.nwu.user.api;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryPatientInfoDto;
import com.nwu.user.model.dto.UpdatePatientInfoDto;
import com.nwu.user.model.po.PatientInfo;
import com.nwu.user.service.IPatientInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 就诊人表 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-06
 */
@RestController
@Api(tags = "就诊人接口")
@RequestMapping("/patient")
public class PatientInfoController {
    @Autowired
    IPatientInfoService iPatientInfoService;

    @ApiOperation(value = "根据用户id查询就诊人")
    @GetMapping("/listByUserId")
    public Result<PageResult<PatientInfo>> getPatientListByUserId(Long userId, PageParams pageParams) {
        return iPatientInfoService.getPatientListByUserId(userId, pageParams);
    }

    @ApiOperation(value = "删除就诊人")
    @DeleteMapping("")
    public Result deletePatientById(Long id) {
        return iPatientInfoService.deletePatientById(id);
    }

    @ApiOperation(value = "查询单条就诊人信息")
    @GetMapping("")
    public Result<PatientInfo> getPatientInfoById(Long id) {
        return iPatientInfoService.getPatientInfoById(id);
    }

    @PutMapping("")
    @ApiOperation("修改就诊人")
    public Result updatePatientInfo(@RequestBody(required = false) UpdatePatientInfoDto updatePatientInfoDto) {

        return iPatientInfoService.updatePatientInfo(updatePatientInfoDto);
    }

    @PostMapping("/list")
    @ApiOperation("就诊人查询接口")
    public Result<PageResult<PatientInfo>> list(PageParams pageParams, @RequestBody(required = false) QueryPatientInfoDto queryPatientInfoDto) {

        return iPatientInfoService.queryPatientInfoList(pageParams, queryPatientInfoDto);
    }


}
