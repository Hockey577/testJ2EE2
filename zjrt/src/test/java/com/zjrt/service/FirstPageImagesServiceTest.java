package com.zjrt.service;

import com.zjrt.entity.FirstPageImagesEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2018-2-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class FirstPageImagesServiceTest {
    @Autowired
    private FirstPageImagesService firstPageImagesService;

    @Test
    public void deleteById() throws Exception {
        long imageid = 4;
        int i = firstPageImagesService.deleteById(imageid);
        System.out.println("测试——————"+ i);
    }

    @Test
    public void queryById() throws Exception {
        long imageid = 5;
        FirstPageImagesEntity f = firstPageImagesService.queryById(imageid);
        System.out.println("测试——————"+ f);
    }

}