package com.nwu.registration.api;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.registration.model.po.OrderInfo;
import com.nwu.registration.service.IOrderInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-24
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    IOrderInfoService iOrderInfoService;

    @PostMapping("/list")
    @ApiOperation("挂号查询接口")
    public Result<PageResult<OrderInfo>> list(PageParams pageParams) {
        return iOrderInfoService.queryOrderInfoList(pageParams);
    }
}
