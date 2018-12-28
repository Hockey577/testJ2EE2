package com.zjrt.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018-2-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class FirstPageImagesDaoTest {
    @Resource
    private FirstPageImagesDao firstPageImagesDao;

    @Test
    public void deleteById() throws Exception {
        long imageid = 3;
        int i = firstPageImagesDao.deleteById(imageid);
        System.out.println("测试————————————"+ i);
    }

}