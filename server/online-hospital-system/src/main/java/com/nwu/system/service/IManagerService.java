package com.nwu.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.system.model.dto.ManagerLoginDto;
import com.nwu.system.model.po.Manager;


public interface IManagerService extends IService<Manager> {

    Result<?> login(ManagerLoginDto managerLoginDto);
}
