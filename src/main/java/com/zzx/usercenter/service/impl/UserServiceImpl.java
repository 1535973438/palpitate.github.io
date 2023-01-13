package com.zzx.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.usercenter.model.domain.User;
import com.zzx.usercenter.service.UserService;
import com.zzx.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zzx
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-01-13 15:24:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




