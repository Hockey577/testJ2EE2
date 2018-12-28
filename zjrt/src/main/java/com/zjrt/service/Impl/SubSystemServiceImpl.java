package com.zjrt.service.Impl;

import com.zjrt.dao.SubSystemDao;
import com.zjrt.entity.SubSystemEntity;
import com.zjrt.service.SubSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
@Service
@Transactional
public class SubSystemServiceImpl implements SubSystemService{
    @Autowired
    private SubSystemDao subSystemDao;

    @Override
    public List<SubSystemEntity> queryByCluster(long systemid) {
        return subSystemDao.queryByCluster(systemid);
    }

    @Override
    public int addSub(SubSystemEntity sub) {
        return subSystemDao.addSub(sub);
    }

    @Override
    public SubSystemEntity queryByName(SubSystemEntity sub) {
        return subSystemDao.queryByName(sub);
    }

    @Override
    public int updateSub(SubSystemEntity sub) {
        return subSystemDao.updateSub(sub);
    }

    @Override
    public int deleteById(SubSystemEntity sub) {
        Integer integer = subSystemDao.deleteById(sub);
        return integer;
    }
}
