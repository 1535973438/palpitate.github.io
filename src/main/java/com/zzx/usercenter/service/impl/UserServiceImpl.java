package com.zzx.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.usercenter.mapper.UserMapper;
import com.zzx.usercenter.model.domain.User;
import com.zzx.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zzx
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-01-13 15:24:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    UserMapper userMapper;
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "zzx";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //校验
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4)
            return -1;
        if (userPassword.length() < 8 || checkPassword.length() < 8)
            return -1;
        //账户不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        //密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0)
            return -1;
        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean save = this.save(user);
        if (!save)
            return -1;
        return user.getId();
    }
}




