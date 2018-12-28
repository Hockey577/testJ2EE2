package com.zjrt.service.Impl;

import com.zjrt.dao.LogInfoDao;
import com.zjrt.entity.LogInfoEntity;
import com.zjrt.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018-2-19.
 */
@Service
@Transactional
public class LogInfoServiceImpl implements LogInfoService{
    @Autowired
    private LogInfoDao logInfoDao;

    @Override
    public int addLog(LogInfoEntity logInfoEntity) {
        return logInfoDao.addLog(logInfoEntity);
    }
}
