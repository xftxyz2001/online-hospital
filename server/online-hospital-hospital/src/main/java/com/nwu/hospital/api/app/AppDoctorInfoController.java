package com.nwu.hospital.api.app;

import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.app.QueryInquiryDoctorListDto;
import com.nwu.hospital.model.po.DoctorInfo;
import com.nwu.hospital.model.vo.AppDoctorInfoVo;
import com.nwu.hospital.model.vo.AppQueryInquiryDoctorListVo;
import com.nwu.hospital.service.IDoctorInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 22:31
 **/
@RestController
@RequestMapping("app/doctorInfo")
@Tag(name = "app医生接口")
public class AppDoctorInfoController {
    @Autowired
    IDoctorInfoService iDoctorInfoService;

    @Operation(summary = "根据医院id和门诊id获取在线问诊医生列表")
    @PostMapping("/queryInquiryDoctorList")
    public Result<List<AppQueryInquiryDoctorListVo>> queryInquiryDoctorList(@RequestBody QueryInquiryDoctorListDto queryInquiryDoctorListDto) {
        List<DoctorInfo> list = iDoctorInfoService.appQueryInquiryDoctorList(queryInquiryDoctorListDto);
        List<AppQueryInquiryDoctorListVo> listVos = new ArrayList<>();
        for (DoctorInfo item : list) {
            AppQueryInquiryDoctorListVo appQueryInquiryDoctorListVo = new AppQueryInquiryDoctorListVo();
            BeanUtils.copyProperties(item, appQueryInquiryDoctorListVo);
            listVos.add(appQueryInquiryDoctorListVo);
        }
        return Result.success(listVos);
    }

    @Operation(summary = "app查询单个医生信息")
    @GetMapping("/queryById")
    public Result<AppDoctorInfoVo> queryById(Long doctorId) {
        AppDoctorInfoVo appDoctorInfoVo = iDoctorInfoService.appQueryById(doctorId);
        return Result.success(appDoctorInfoVo);

    }
}
