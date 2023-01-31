package com.zzx.usercenter.service;

import com.zzx.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzx
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-01-13 15:24:38
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetC0de);

    /**
     * 用户登录
     * userAccount 用户账户
     * @param userPassword 用户密码
     * @return 用户数据
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafeUser(User originUser);

    public void userLogout(HttpServletRequest request);
}
