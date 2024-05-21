package com.nwu.registration.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.constant.UserConstant;
import com.nwu.base.context.BaseContext;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.registration.clients.HospitalClient;
import com.nwu.registration.clients.UserClient;
import com.nwu.registration.mapper.OrderInfoMapper;
import com.nwu.registration.mapper.ScheduleMapper;
import com.nwu.registration.model.clientspo.PatientInfo;
import com.nwu.registration.model.dto.InsertOrderDto;
import com.nwu.registration.model.dto.UpdateOrderDto;
import com.nwu.registration.model.po.OrderInfo;
import com.nwu.registration.model.po.Schedule;
import com.nwu.registration.service.IOrderInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-24
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    HospitalClient hospitalClient;
    @Autowired
    UserClient userClient;
    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public void addOrder(InsertOrderDto insertOrderDto) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(insertOrderDto, orderInfo);
        orderInfo.setCreateTime(LocalDateTime.now());
        orderInfo.setUpdateTime(LocalDateTime.now());
        orderInfo.setOrderStatus(0);
        orderInfo.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
        orderInfo.setUserId(BaseContext.getCurrentId());
        Long hospitalId = insertOrderDto.getHospitalId();
        String hospitalName = hospitalClient.getHospitalInfoById(hospitalId).getData().getName();
        Long outpatientId = insertOrderDto.getOutpatientId();
        String outpatientName = hospitalClient.queryOutpatientById(outpatientId).getData().getName();
        Long doctorId = insertOrderDto.getDoctorId();
        String doctorName = hospitalClient.queryDoctorById(doctorId).getData().getName();
        Long patientId = insertOrderDto.getPatientId();
        PatientInfo patientInfo = new PatientInfo();
        patientInfo = userClient.getPatientInfoById(patientId).getData();
        String patientName = patientInfo.getName();
        String phone = patientInfo.getPhone();
        orderInfo.setDoctorName(doctorName);
        orderInfo.setHospitalName(hospitalName);
        orderInfo.setOutpatientName(outpatientName);
        orderInfo.setPatientName(patientName);
        orderInfo.setPatientPhone(phone);
        orderInfoMapper.insert(orderInfo);
        LambdaUpdateWrapper<Schedule> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Schedule::getId, insertOrderDto.getScheduleId()).setSql("available_number=available_number-1");
        scheduleMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public List<OrderInfo> queryAllOrder() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<OrderInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderInfo::getUserId, userId)
                .orderByDesc(OrderInfo::getCreateTime);
        return orderInfoMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void updateOrder(UpdateOrderDto updateOrderDto) {
        LambdaUpdateWrapper<OrderInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        switch (updateOrderDto.getOrderStatus()) {
            case 0:
                break;
            case 1://修改为已完成
                lambdaUpdateWrapper.eq(OrderInfo::getId, updateOrderDto.getId())
                        .set(OrderInfo::getOrderStatus, updateOrderDto.getOrderStatus());
            case 2://修改为已超时
                break;
            case 3://修改为已取消

                lambdaUpdateWrapper.eq(OrderInfo::getId, updateOrderDto.getId())
                        .set(OrderInfo::getOrderStatus, updateOrderDto.getOrderStatus())
                        .ge(OrderInfo::getReserveDate, LocalDate.now());
                LambdaUpdateWrapper<Schedule> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
                OrderInfo orderInfo = getById(updateOrderDto.getId());
                lambdaUpdateWrapper1.eq(Schedule::getId, orderInfo.getScheduleId()).setSql("`available_number`=`available_number`+1");
                scheduleMapper.update(null, lambdaUpdateWrapper1);
                break;
        }
        orderInfoMapper.update(null, lambdaUpdateWrapper);

    }

    @Override
    public Result<PageResult<OrderInfo>> webQueryOrderInfoList(PageParams pageParams) {
        Page<OrderInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        LambdaQueryWrapper<OrderInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderInfo::getDoctorId, BaseContext.getCurrentId());
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, lambdaQueryWrapper);
        List<OrderInfo> orderInfos = orderInfoPage.getRecords();
        Long counts = orderInfoPage.getTotal();
        PageResult<OrderInfo> orderInfoPageResult = new PageResult<>(orderInfos, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(orderInfoPageResult);
    }

    @Override
    public Result<PageResult<OrderInfo>> queryOrderInfoList(PageParams pageParams) {
        Page<OrderInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, null);
        List<OrderInfo> orderInfos = orderInfoPage.getRecords();
        Long counts = orderInfoPage.getTotal();
        PageResult<OrderInfo> orderInfoPageResult = new PageResult<>(orderInfos, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(orderInfoPageResult);
    }
}
