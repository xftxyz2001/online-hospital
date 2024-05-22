package com.nwu.registration.api.web;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.registration.model.po.OrderInfo;
import com.nwu.registration.service.IOrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderInfo/web")
@Tag(name = "web端订单接口")
public class WebOrderInfoController {
    @Autowired
    IOrderInfoService iOrderInfoService;

    @PostMapping("/list")
    @Operation(summary = "挂号查询接口")
    public Result<PageResult<OrderInfo>> list(PageParams pageParams) {

        return iOrderInfoService.webQueryOrderInfoList(pageParams);
    }

}
