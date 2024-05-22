package com.nwu.registration.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.registration.mapper.ReportInfoMapper;
import com.nwu.registration.model.po.ReportInfo;
import com.nwu.registration.service.IReportInfoService;
import org.springframework.stereotype.Service;

@Service
public class ReportInfoServiceImpl extends ServiceImpl<ReportInfoMapper, ReportInfo> implements IReportInfoService {
}
