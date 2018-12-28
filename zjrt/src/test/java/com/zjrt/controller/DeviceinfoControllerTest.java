package com.zjrt.controller;

import com.zjrt.dto.ListResult;
import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.dao.DeviceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2018-3-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class DeviceinfoControllerTest {
    @Autowired
    private DeviceinfoController deviceinfoController;

    @Test
    public void queryByTerm() throws Exception {
        DeviceVO d = new DeviceVO("6","","");
        ListResult<DeviceInfoEntity> result =
                deviceinfoController.queryByTerm(d);
        System.out.println(result.getData());
    }

}