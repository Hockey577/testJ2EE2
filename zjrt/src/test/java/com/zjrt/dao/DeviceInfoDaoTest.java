package com.zjrt.dao;

import com.zjrt.entity.DeviceInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018-2-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class DeviceInfoDaoTest {
    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Test
    public void queryALL() throws Exception {
        List<DeviceInfoEntity> list = deviceInfoDao.queryALL(1,1);
        System.out.println("测试-----------" + list);
    }

}