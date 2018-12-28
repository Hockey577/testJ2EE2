package com.zjrt.dao;

import com.zjrt.entity.ClusterSystemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
public interface ClusterSystemDao {
    /**
     * 获取集群列表
     * @return
     */
    List<ClusterSystemEntity> queryAll();

    /**
     * 删除集群
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 添加集群
     * @param cluster
     * @return
     */
    int addCluster(ClusterSystemEntity cluster);

    List<ClusterSystemEntity> queryAllName();
}
