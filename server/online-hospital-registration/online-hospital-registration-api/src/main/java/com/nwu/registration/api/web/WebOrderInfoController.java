package com.nwu.registration.api.web;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.registration.model.po.OrderInfo;
import com.nwu.registration.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderInfo/web")
@Api(tags = "web端订单接口")
public class WebOrderInfoController {
    @Autowired
    IOrderInfoService iOrderInfoService;

    @PostMapping("/list")
    @ApiOperation("挂号查询接口")
    public Result<PageResult<OrderInfo>> list(PageParams pageParams) {

        return iOrderInfoService.webQueryOrderInfoList(pageParams);
    }

}
