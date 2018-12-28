package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.entity.FirstPageImagesEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator on 2018-2-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class FirstPageImagesControllerTest {
    @Autowired
    private FirstPageImagesController f;

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void deleteById() throws Exception {
        FirstPageImagesEntity image = new FirstPageImagesEntity(5) ;
        CommonResult c= f.deleteById(image);
        System.out.println("测试——————"+ c);
    }

}