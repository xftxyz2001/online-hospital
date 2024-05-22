package com.nwu.hospital.api.admin;


import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.admin.UpdateHospitalDto;
import com.nwu.hospital.model.po.HospitalInfo;
import com.nwu.hospital.service.IHospitalInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 分院信息 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@RestController
@Api(tags = "医院接口")
@RequestMapping("/hospital")
public class HospitalInfoController {

    @Autowired
    IHospitalInfoService iHospitalInfoService;

    @GetMapping("")
    @ApiOperation(value = "获取全部医院信息")
    public Result<List<HospitalInfo>> getAllHospitalInfo() {

        return Result.success(iHospitalInfoService.list());
    }

    @ApiOperation(value = "根据id查询医院")
    @GetMapping("/queryById")
    public Result<HospitalInfo> getHospitalInfoById(Long id) {
        return Result.success(iHospitalInfoService.getById(id));
    }

    @ApiOperation(value = "更新医院信息")
    @PutMapping("/update")
    public Result<?> update(@RequestBody UpdateHospitalDto updateHospitalDto) {
        iHospitalInfoService.updateHospital(updateHospitalDto);
        return Result.success();


    }
}
