package com.nwu.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.system.model.dto.ManagerLoginDto;
import com.nwu.system.model.po.Manager;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-13
 */
public interface IManagerService extends IService<Manager> {

    Result login(ManagerLoginDto managerLoginDto);
}
