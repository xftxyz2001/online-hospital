package com.nwu.inquiry.clients;


import com.nwu.base.model.Result;
import com.nwu.inquiry.model.clientspo.PatientInfo;
import com.nwu.inquiry.model.clientspo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/24 17:20
 **/
@FeignClient(value = "user-api")
public interface UserClient {
    @GetMapping("/user/patient")
    Result<PatientInfo> getPatientInfoById(@RequestParam("id") Long id);

    @GetMapping("/user/user/{id}")
    Result<UserInfo> getUserInfoById(@PathVariable("id") Long id);
}
