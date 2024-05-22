package com.nwu.hospital.api.app;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/hospital")
@Api(tags = "app医院接口")
public class AppHospitalController {

}
