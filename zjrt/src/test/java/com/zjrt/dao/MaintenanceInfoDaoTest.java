package com.zjrt.dao;

import com.zjrt.entity.MaintainEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by Apple on 2018/2/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//告诉junit spring配置文件
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class MaintenanceInfoDaoTest {
    @Autowired
    MaintenanceInfoDao dao;

//    @Test
//    public void queryALL() throws Exception {
//        List<MaintainEntity> list = dao.queryALL();
//        System.err.println(list);
//    }

    @Test
    public void createMaintain() throws Exception {
        MaintainEntity m = new MaintainEntity();
        m.setTitle("11");
        m.setDevicename("1");
        Date d  = new Date();
        m.setAddtime(d);
        m.setUsername("11");
        m.setDescription("1");
        m.setSolution("1");
        m.setType(0);
        m.setPhoto1("1");
        m.setPhoto2("2");
        System.out.println(m);
        int i = dao.createMaintain(m);
        System.out.println("测试-------------------------" + i);
    }

    @Test
    public void updateMaintain() throws Exception {
    }

    @Test
    public void queryByName() throws Exception {
        MaintainEntity m = new MaintainEntity();
        m.setDevicename("1");
        List<MaintainEntity> list = dao.queryByName(m);
        System.out.println("测试------------" + list);
    }


}