package com.nwu.registration.clients;


import com.nwu.base.model.Result;
import com.nwu.registration.model.clientspo.DoctorInfo;
import com.nwu.registration.model.clientspo.HospitalInfo;
import com.nwu.registration.model.clientspo.OutpatientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "hospital-api")
public interface HospitalClient {
    @GetMapping("/hospital/doctorInfo/queryById")
    Result<DoctorInfo> queryDoctorById(@RequestParam("doctorId") Long doctorId);

    @GetMapping("/hospital/hospital/queryById")
    Result<HospitalInfo> getHospitalInfoById(@RequestParam("id") Long id);

    @GetMapping("/hospital/outpatientInfo/queryById")
    Result<OutpatientInfo> queryOutpatientById(@RequestParam("id") Long id);
}
