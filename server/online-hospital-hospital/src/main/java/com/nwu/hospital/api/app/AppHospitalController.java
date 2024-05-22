package com.nwu.hospital.api.app;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/hospital")
@Tag(name = "app医院接口")
public class AppHospitalController {

}
