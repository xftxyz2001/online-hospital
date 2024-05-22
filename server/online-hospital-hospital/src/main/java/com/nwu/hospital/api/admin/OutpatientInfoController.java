package com.nwu.hospital.api.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.admin.AddOutpatientDto;
import com.nwu.hospital.model.dto.admin.UpdateOutpatientDto;
import com.nwu.hospital.model.po.OutpatientInfo;
import com.nwu.hospital.model.vo.AppOutpatientListOrderByLetter;
import com.nwu.hospital.model.vo.AppOutpatientListVo;
import com.nwu.hospital.service.IOutpatientInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门诊信息表 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/outpatientInfo")
@Tag(name = "门诊接口")
public class OutpatientInfoController {
    @Autowired
    IOutpatientInfoService iOutpatientInfoService;

    @GetMapping("/queryOutpatientInfoByHospitalAndSpecialistId")
    @Operation(summary = "根据医院id和专科id获取门诊信息")
    public Result<List<OutpatientInfo>> queryOutpatientInfoByHospitalAndSpecialistId(Long hospitalId, Long specialistId) {
        return iOutpatientInfoService.queryOutpatientInfoByHospitalAndSpecialistId(hospitalId, specialistId);
    }

    @GetMapping("/queryById")
    @Operation(summary = "根据id获取门诊信息")
    public Result<OutpatientInfo> queryOutpatientById(Long id) {
        return Result.success(iOutpatientInfoService.getById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "添加门诊")
    public Result<?> add(@RequestBody AddOutpatientDto addOutpatientDto) throws BadHanyuPinyinOutputFormatCombination {
        OutpatientInfo outpatientInfo = new OutpatientInfo();
        BeanUtils.copyProperties(addOutpatientDto, outpatientInfo);
        outpatientInfo.setCreateTime(LocalDateTime.now())
                .setModifiedTime(LocalDateTime.now());
        if (outpatientInfo.getName().charAt(0) >= 'A' && outpatientInfo.getName().charAt(0) <= 'Z') {
            outpatientInfo.setFirstLetter(String.valueOf(outpatientInfo.getName().charAt(0)));
        } else {
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
            hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(addOutpatientDto.getName().charAt(0), hanyuPinyinOutputFormat);
            outpatientInfo.setFirstLetter(String.valueOf(pinyinArray[0].charAt(0)));
        }
        iOutpatientInfoService.save(outpatientInfo);
        return Result.success();
    }

    @PutMapping("/update")
    @Operation(summary = "修改门诊")
    public Result<?> update(@RequestBody UpdateOutpatientDto updateOutpatientDto) {
        LambdaUpdateWrapper<OutpatientInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(OutpatientInfo::getId, updateOutpatientDto.getId())
                .set(StringUtils.isNotEmpty(updateOutpatientDto.getName()), OutpatientInfo::getName, updateOutpatientDto.getName())
                .set(StringUtils.isNotEmpty(updateOutpatientDto.getDisc()), OutpatientInfo::getDisc, updateOutpatientDto.getDisc())
                .set(OutpatientInfo::getModifiedTime, LocalDateTime.now());
        iOutpatientInfoService.update(lambdaUpdateWrapper);
        return Result.success();
    }

    @Operation(summary = "返回首字母排序门诊列表")
    @GetMapping("/order-letter-list")
    public Result<AppOutpatientListOrderByLetter> orderByLetterList(Long hospitalId) {
        LambdaQueryWrapper<OutpatientInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OutpatientInfo::getHospitalId, hospitalId);
        List<OutpatientInfo> outpatientInfos = iOutpatientInfoService.list(lambdaQueryWrapper);
        Map<String, List<AppOutpatientListVo>> hashMap = new HashMap<String, List<AppOutpatientListVo>>();
        for (OutpatientInfo outpatientInfo : outpatientInfos) {
            if (hashMap.containsKey(outpatientInfo.getFirstLetter())) {
                AppOutpatientListVo appOutpatientListVo = new AppOutpatientListVo();
                BeanUtils.copyProperties(outpatientInfo, appOutpatientListVo);
                hashMap.get(outpatientInfo.getFirstLetter()).add(appOutpatientListVo);
            } else {
                List<AppOutpatientListVo> tempList = new ArrayList<AppOutpatientListVo>();
                AppOutpatientListVo appOutpatientListVo = new AppOutpatientListVo();
                BeanUtils.copyProperties(outpatientInfo, appOutpatientListVo);
                tempList.add(appOutpatientListVo);
                hashMap.put(outpatientInfo.getFirstLetter(), tempList);
            }
        }
        AppOutpatientListOrderByLetter appOutpatientListOrderByLetter = new AppOutpatientListOrderByLetter();
        List<String> letters = new ArrayList<>(hashMap.keySet());
        List<List<AppOutpatientListVo>> appOutpatientListVos = new ArrayList<>(hashMap.values());
        appOutpatientListOrderByLetter.setLetterList(letters);
        appOutpatientListOrderByLetter.setOutpatientList(appOutpatientListVos);
        return Result.success(appOutpatientListOrderByLetter);
    }

}
