package com.zjrt.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator on 2018-2-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class SubSystemControllerTest {

    @Autowired
    private SubSystemController s;

    @Autowired
    private WebApplicationContext wac;

//    @Test
//    public void getByCluster() throws Exception {
//        long i = 1;
//        ListResult<SubSystemEntity> listResult = s.getByCluster(i);
//        System.out.println(listResult);
//    }

}