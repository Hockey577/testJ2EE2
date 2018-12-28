package com.zjrt.service.Impl;

import com.zjrt.dao.ClusterSystemDao;
import com.zjrt.entity.ClusterSystemEntity;
import com.zjrt.service.ClusterSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
@Service
@Transactional
public class ClusterSystemServiceImpl implements ClusterSystemService {
    @Autowired
    private ClusterSystemDao clusterSystemDao;

    @Override
    public List<ClusterSystemEntity> queryAll() {
        return clusterSystemDao.queryAll();
    }

    @Override
    public int deleteById(long id) {
        return clusterSystemDao.deleteById(id);
    }

    @Override
    public int addCluster(ClusterSystemEntity cluster) {
        return clusterSystemDao.addCluster(cluster);
    }

    @Override
    public List<ClusterSystemEntity> queryAllName() {
        return clusterSystemDao.queryAllName();
    }
}
