package com.zjrt.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Apple on 2018/2/12.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml"})
public class FileUtilsTest {

    @Test
    public void getString() {
        //String path = FileUtils.getInstance().getPath("fileUpload");
        //System.err.println(path);
    }
}