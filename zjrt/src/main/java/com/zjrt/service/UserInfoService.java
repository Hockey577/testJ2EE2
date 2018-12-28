package com.zjrt.service;


import com.zjrt.entity.UserInfoEntity;

/**
 * Created by Administrator on 2018-2-8.
 */
public interface UserInfoService {
    /**
     * 注册
     *
     * @param userInfoEntity
     * @return
     */
    int addUser(UserInfoEntity userInfoEntity);

    /**
     * 获取用户信息
     *
     * @param userInfoEntity
     * @return
     */
    UserInfoEntity checkLogin(UserInfoEntity userInfoEntity);


    //根据用户名查找
    UserInfoEntity queryByName(UserInfoEntity userInfoEntity);

    /**
     * 修改密码
     * @param userInfoEntity
     * @return
     */
    int modifyPwd(UserInfoEntity userInfoEntity);
}
