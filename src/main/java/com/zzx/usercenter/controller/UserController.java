package com.zzx.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.usercenter.common.BaseResponse;
import com.zzx.usercenter.common.ErrorCode;
import com.zzx.usercenter.common.ResultUtils;
import com.zzx.usercenter.constant.UserConstant;
import com.zzx.usercenter.exception.BusinessException;
import com.zzx.usercenter.model.domain.User;
import com.zzx.usercenter.model.request.UserLoginRequest;
import com.zzx.usercenter.model.request.UserRegisterRequest;
import com.zzx.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static com.zzx.usercenter.constant.UserConstant.ADMIN_ROLE;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode=userRegisterRequest.getPlanetCode();
        if (StringUtils.isAllBlank(userPassword, userPassword, checkPassword,planetCode))
            return null;
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAllBlank(userPassword, userPassword))
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null)
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        Long id = user.getId();
        //todo 校验用户是否合法
        User byId = userService.getById(id);
        User safeUser = userService.getSafeUser(byId);
        return ResultUtils.success(safeUser);
    }


    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request) {
        if (!isAdmin(request))
            throw new BusinessException(ErrorCode.NO_AUTH);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> collect = userList.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }

    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody Long id, HttpServletRequest request) {
        if (!isAdmin(request))
            throw new BusinessException(ErrorCode.NO_AUTH);
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean flag = userService.removeById(id);
        return ResultUtils.success(flag);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        userService.userLogout(request);
        return ResultUtils.success(1);
    }

    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user == null || user.getUserRole() != ADMIN_ROLE;
    }

}
