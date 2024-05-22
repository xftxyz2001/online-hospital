package com.nwu.hospital.api.admin;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.admin.AddSpecialistDto;
import com.nwu.hospital.model.dto.admin.UpdateSpecialistDto;
import com.nwu.hospital.model.po.SpecialistInfo;
import com.nwu.hospital.model.vo.AppSpecialistTreeVo;
import com.nwu.hospital.model.vo.SpecialistTreeVo;
import com.nwu.hospital.service.ISpecialistInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 专科 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@RestController
@Tag(name = "专科接口")
@RequestMapping("/specialist")
public class SpecialistInfoController {

    @Autowired
    ISpecialistInfoService iSpecialistInfoService;

    @Operation(summary = "根据医院id查询专科")
    @GetMapping("/querySpecialistByHospitalId")
    public Result<List<SpecialistInfo>> getSpecialistByHospitalId(Long id) {
        return iSpecialistInfoService.getSpecialistByHospitalId(id);
    }

    @Operation(summary = "根据id查询专科")
    @GetMapping("/queryById")
    public Result<SpecialistInfo> getSpecialistInfoById(Long id) {
        return Result.success(iSpecialistInfoService.getById(id));
    }

    @Operation(summary = "根据医院id获取专科诊室树状信息")
    @GetMapping("/querySpecialistTree")
    public Result<List<SpecialistTreeVo>> querySpecialistTree(Long hospitalId) {
        return iSpecialistInfoService.querySpecialistTree(hospitalId);
    }

    @Operation(summary = "根据医院id获取小程序专科诊室树状信息")
    @GetMapping("/queryAppSpecialistTree")
    public Result<List<AppSpecialistTreeVo>> queryAppSpecialistTree(Long hospitalId) {
        return iSpecialistInfoService.queryAppSpecialistTree(hospitalId);
    }

    @Operation(summary = "添加专科")
    @PostMapping("/add")
    public Result<?> add(@RequestBody AddSpecialistDto addSpecialistDto) {
        SpecialistInfo specialistInfo = new SpecialistInfo();
        BeanUtils.copyProperties(addSpecialistDto, specialistInfo);
        specialistInfo.setCreateTime(LocalDateTime.now())
                .setModifiedTime(LocalDateTime.now());
        iSpecialistInfoService.save(specialistInfo);
        return Result.success();
    }

    @Operation(summary = "修改专科")
    @PutMapping("/update")
    public Result<?> update(@RequestBody UpdateSpecialistDto updateSpecialistDto) {
        LambdaUpdateWrapper<SpecialistInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SpecialistInfo::getId, updateSpecialistDto.getId())
                .set(StringUtils.isNotEmpty(updateSpecialistDto.getName()), SpecialistInfo::getName, updateSpecialistDto.getName())
                .set(StringUtils.isNotEmpty(updateSpecialistDto.getDisc()), SpecialistInfo::getDisc, updateSpecialistDto.getDisc())
                .set(SpecialistInfo::getModifiedTime, LocalDateTime.now());
        iSpecialistInfoService.update(lambdaUpdateWrapper);
        return Result.success();
    }


}
