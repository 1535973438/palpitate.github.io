package com.zzx.usercenter.service;

import com.zzx.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void TestAddUser(){
        User one = userService.getOne(null);
    }

    @Test
    void userRegister() {
        String account="zzxzzx";
        String password="12345678 ";
        String checkPassword="12345678 ";
        long l = userService.userRegister(account, password, checkPassword);
    }
}