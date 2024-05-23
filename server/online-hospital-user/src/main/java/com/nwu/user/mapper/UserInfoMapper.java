package com.nwu.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.user.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
