package com.zjrt.service.Impl;

import com.zjrt.dao.UserInfoDao;
import com.zjrt.entity.UserInfoEntity;
import com.zjrt.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018-2-8.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    private Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Override
    public int addUser(UserInfoEntity userInfoEntity) {
        int i = userInfoDao.addUser(userInfoEntity);
        return i;
    }

    public UserInfoEntity checkLogin(UserInfoEntity userInfoEntity) {
        UserInfoEntity userInfo = userInfoDao.checklogin(userInfoEntity.getUsername());
        return userInfo;
    }

    @Override
    public UserInfoEntity queryByName(UserInfoEntity userInfoEntity) {
        UserInfoEntity user = userInfoDao.queryByName(userInfoEntity.getUsername());
        return user;
    }

    @Override
    public int modifyPwd(UserInfoEntity userInfoEntity) {
        int i = userInfoDao.modifyPwd(userInfoEntity);
        return i;
    }
}
