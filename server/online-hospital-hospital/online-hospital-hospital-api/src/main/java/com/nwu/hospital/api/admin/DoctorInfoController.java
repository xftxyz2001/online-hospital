package com.nwu.hospital.api.admin;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.admin.AddDoctorDto;
import com.nwu.hospital.model.dto.admin.QueryDoctorInfoPageDto;
import com.nwu.hospital.model.dto.admin.UpdateDoctorDto;
import com.nwu.hospital.model.po.DoctorInfo;
import com.nwu.hospital.model.vo.DoctorInfoPageVo;
import com.nwu.hospital.service.IDoctorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 医生信息表 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/doctorInfo")
@Api(tags = "医生接口")
public class DoctorInfoController {
    @Autowired
    IDoctorInfoService iDoctorInfoService;

    @ApiOperation(value = "根据医生id查询医生")
    @GetMapping("/queryById")
    public Result<DoctorInfo> queryById(Long doctorId) {
        return Result.success(iDoctorInfoService.getById(doctorId));
    }

    @ApiOperation(value = "根据医院和门诊id查询医生列表")
    @GetMapping("/queryByHospitalAndOutpatientId")
    public Result<List<DoctorInfo>> queryByHospitalAndOutpatientId(Long hospitalId, Long outpatientId) {
        return iDoctorInfoService.queryByHospitalAndOutpatientId(hospitalId, outpatientId);
    }

    @ApiOperation(value = "添加医生")
    @PostMapping("/add")
    public Result add(@RequestBody AddDoctorDto addDoctorDto) {
        DoctorInfo doctorInfo = new DoctorInfo();
        BeanUtils.copyProperties(addDoctorDto, doctorInfo);
        doctorInfo.setCreateTime(LocalDateTime.now())
                .setModifiedTime(LocalDateTime.now());
        iDoctorInfoService.save(doctorInfo);
        String username = "netmedic" + String.valueOf(doctorInfo.getId());
        UpdateDoctorDto updateDoctorDto = new UpdateDoctorDto();
        updateDoctorDto.setId(doctorInfo.getId());
        System.err.println(doctorInfo.getId());
        updateDoctorDto.setUsername(username);
        System.err.println(username);
        update(updateDoctorDto);
        return Result.success();
    }

    @ApiOperation(value = "修改医生")
    @PutMapping("/update")
    public Result update(@RequestBody UpdateDoctorDto updateDoctorDto) {
        LambdaUpdateWrapper<DoctorInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(DoctorInfo::getId, updateDoctorDto.getId())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getName()), DoctorInfo::getName, updateDoctorDto.getName())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getIntroduce()), DoctorInfo::getIntroduce, updateDoctorDto.getIntroduce())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getPicture()), DoctorInfo::getPicture, updateDoctorDto.getPicture())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getProfessionalTitle()), DoctorInfo::getProfessionalTitle, updateDoctorDto.getProfessionalTitle())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getGender()), DoctorInfo::getGender, updateDoctorDto.getGender())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getIsInquiry()), DoctorInfo::getIsInquiry, updateDoctorDto.getIsInquiry())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getUsername()), DoctorInfo::getUsername, updateDoctorDto.getUsername())
                .set(StringUtils.isNotEmpty(updateDoctorDto.getPassword()), DoctorInfo::getPassword, updateDoctorDto.getPassword())
                .set(DoctorInfo::getModifiedTime, LocalDateTime.now());
        iDoctorInfoService.update(lambdaUpdateWrapper);
        return Result.success();
    }

    @ApiOperation(value = "分页条件查询医生")
    @PostMapping("/queryPage")
    public Result<PageResult<DoctorInfoPageVo>> queryPage(PageParams pageParams, @RequestBody(required = false) QueryDoctorInfoPageDto queryDoctorInfoPageDto) {
        return iDoctorInfoService.queryPageDoctorList(pageParams, queryDoctorInfoPageDto);
    }
}
