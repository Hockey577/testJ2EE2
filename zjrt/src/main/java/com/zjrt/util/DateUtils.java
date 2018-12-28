package com.zjrt.util;


import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件操作工具类，主要操作文件上传和
 * Created by Apple on 2018/2/11.
 */
public class DateUtils {
    private static DateUtils utils = null;
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);

    private DateUtils() {
    }

    public synchronized static DateUtils getInstance() {
        if (utils == null) {
            utils = new DateUtils();
        }
        return utils;
    }

    /**
     * 获取当前日期，日期格式默认为 yyyy-MM-dd HH:MM:SS
     *
     * @return
     */
    public String getNowTime() {
        Date now = new Date();
        return format(now, DEFAULT_PATTERN);
    }

    /**
     * 格式化日期，日期格式默认为 yyyy-MM-dd HH:MM:SS
     *
     * @return
     */
    public String format(Date date, String pattern) {
        if (StringUtils.isNotEmpty(pattern)) {
            formatter = new SimpleDateFormat(pattern);
        }
        return formatter.format(date);
    }

}
