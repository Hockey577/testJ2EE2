package com.zjrt.dao;

import com.zjrt.entity.LogInfoEntity;

/**
 * Created by Administrator on 2018-2-19.
 */
public interface LogInfoDao {
    /**
     * 添加登录日志
     */
    int addLog(LogInfoEntity logInfoEntity);
}
