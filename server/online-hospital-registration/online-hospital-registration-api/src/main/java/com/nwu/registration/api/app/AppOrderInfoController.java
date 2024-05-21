package com.nwu.registration.api.app;

import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.InsertOrderDto;
import com.nwu.registration.model.dto.UpdateOrderDto;
import com.nwu.registration.model.po.OrderInfo;
import com.nwu.registration.model.vo.AppQueryAllOrderVo;
import com.nwu.registration.model.vo.AppQueryOneOrderVo;
import com.nwu.registration.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/24 16:09
 **/
@RestController
@RequestMapping("/orderInfo/app")
@Api(tags = "app订单接口")
public class AppOrderInfoController {
    @Autowired
    IOrderInfoService iOrderInfoService;

    @PostMapping("/add")
    @ApiOperation(value = "新增预约订单")
    public Result addOrder(@RequestBody InsertOrderDto insertOrderDto) {
        iOrderInfoService.addOrder(insertOrderDto);
        return Result.success();
    }


    @GetMapping("/queryAll")
    @ApiOperation(value = "查询所有订单信息")
    public Result<List<AppQueryAllOrderVo>> queryAllOrder() {
        List<OrderInfo> orderInfos = iOrderInfoService.queryAllOrder();
        List<AppQueryAllOrderVo> appQueryAllOrderVos = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfos) {
            AppQueryAllOrderVo appQueryAllOrderVo = new AppQueryAllOrderVo();
            BeanUtils.copyProperties(orderInfo, appQueryAllOrderVo);
            appQueryAllOrderVo.setId(String.valueOf(orderInfo.getId()));
            appQueryAllOrderVos.add(appQueryAllOrderVo);
        }
        return Result.success(appQueryAllOrderVos);
    }

    @GetMapping("/queryOne")
    @ApiOperation(value = "查询单个订单信息")
    public Result<AppQueryOneOrderVo> queryOneOrder(Long id) {
        OrderInfo orderInfo = iOrderInfoService.getById(id);
        AppQueryOneOrderVo appQueryOneOrderVo = new AppQueryOneOrderVo();
        BeanUtils.copyProperties(orderInfo, appQueryOneOrderVo);
        appQueryOneOrderVo.setId(String.valueOf(orderInfo.getId()));
        return Result.success(appQueryOneOrderVo);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新订单")
    public Result update(@RequestBody UpdateOrderDto updateOrderDto) {
        iOrderInfoService.updateOrder(updateOrderDto);
        return Result.success();
    }
}
