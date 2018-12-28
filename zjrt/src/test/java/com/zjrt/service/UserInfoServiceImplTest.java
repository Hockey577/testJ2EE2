package com.zjrt.service;

import com.zjrt.entity.UserInfoEntity;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2018-2-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class UserInfoServiceImplTest extends TestCase {
    @Autowired
    private UserInfoService userInfoService;

    private Logger logger = Logger.getLogger(this.getClass());
    @Test
    public void testAddUser() throws Exception {
        UserInfoEntity userInfoEntity = new UserInfoEntity(11,"小绿",
                "123456",1,"15869191710",1234);
        int i = userInfoService.addUser(userInfoEntity);
        logger.debug("xxxxx======"+i);
        System.out.println("测试：-------------"+i);
    }


    public void testCheckLogin() throws Exception {
    }



}