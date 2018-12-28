package com.zjrt.dao;

import com.zjrt.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018-2-8.
 */
public interface UserInfoDao {
    /**
     * 注册
     *
     * @param userInfoEntity
     * @return
     */
    int addUser(UserInfoEntity userInfoEntity);

    /**
     * 登录
     *
     * @param username
     * @return
     */
    UserInfoEntity checklogin(String username);

    //查找用户名是否存在
    UserInfoEntity queryByName(String username);

    /**
     * 查询用户列表
     *
     * @return
     */
    List<UserInfoEntity> queryAllUsers();

    /**
     * 修改密码
     * @param userInfoEntity
     * @return
     */
    int modifyPwd(UserInfoEntity userInfoEntity);
}
