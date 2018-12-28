package com.zjrt.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by Apple on 2018/2/11.
 */
public class DateUtilsTest {
    @Test
    public void getInstance() throws Exception {
        String time = DateUtils.getInstance().format(new Date(),"");
        System.err.println(time);
    }

    
}