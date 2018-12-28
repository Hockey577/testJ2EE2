package com.zjrt.service;

import com.zjrt.entity.SubSystemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class SubSystemServiceTest {
    @Autowired
    private SubSystemService subSystemService;

    @Test
    public void queryByCluster() throws Exception {
        List<SubSystemEntity> list = subSystemService.queryByCluster(1);
        System.out.println("测试-------------"+list);
    }

}