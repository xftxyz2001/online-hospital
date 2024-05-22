package com.nwu.registration.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.InsertOrderDto;
import com.nwu.registration.model.dto.UpdateOrderDto;
import com.nwu.registration.model.po.OrderInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-24
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    void addOrder(InsertOrderDto insertOrderDto);

    List<OrderInfo> queryAllOrder();

    void updateOrder(UpdateOrderDto updateOrderDto);

    Result<PageResult<OrderInfo>> webQueryOrderInfoList(PageParams pageParams);

    Result<PageResult<OrderInfo>> queryOrderInfoList(PageParams pageParams);
}
