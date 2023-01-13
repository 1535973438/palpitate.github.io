package com.zzx.usercenter.mapper;

import com.zzx.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzx
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-01-13 15:24:38
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




