package com.zjrt.service;

import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.service.Impl.DeviceinfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018-3-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class DeviceinfoServiceTest {
    @Autowired
    private DeviceinfoService deviceinfoService;

    @Test
    public void queryByTerm() throws Exception {
        List<DeviceInfoEntity> list =
                deviceinfoService.queryByTerm("5","","");
        System.out.println(list);
        System.out.println(list.size());
    }

}