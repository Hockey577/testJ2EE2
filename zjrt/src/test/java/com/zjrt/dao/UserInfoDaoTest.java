package com.zjrt.dao;

import com.zjrt.entity.UserInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2017-8-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath*:spring/spring-dao.xml"})
public class UserInfoDaoTest {
    @Resource
    private UserInfoDao userInfoDao;

    @Test
    public void testAddUser() throws Exception {
//        username,password,type,phonenum,code
        UserInfoEntity userInfoEntity = new UserInfoEntity(12,"小一",
                "123456",1,"15869191710",1234);
        int i = userInfoDao.addUser(userInfoEntity);

        System.out.println("测试：-------------------"+i);
    }
}
