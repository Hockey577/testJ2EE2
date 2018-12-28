package com.zjrt.service;

import com.zjrt.entity.ClusterSystemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
public interface ClusterSystemService {
    List<ClusterSystemEntity> queryAll();
    int deleteById(long id);
    int addCluster(ClusterSystemEntity cluster);
    List<ClusterSystemEntity> queryAllName();
}
