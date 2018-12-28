package com.zjrt.dao;

import com.zjrt.entity.SubSystemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
public interface SubSystemDao {
    /**
     * 根据集群id获取该集群所有子系统
     * @return
     */
    List<SubSystemEntity> queryByCluster(long systemid);

    /**
     * 添加子系统
     * @param sub
     * @return
     */
    int addSub(SubSystemEntity sub);

    /**
     * 根据子系统名字查询
     * @param sub
     * @return
     */
    SubSystemEntity queryByName(SubSystemEntity sub);

    /**
     * 修改子系统
     * @param sub
     * @return
     */
    int updateSub(SubSystemEntity sub);

    /**
     * 删除子系统
     * @param sub
     * @return
     */
    int deleteById(SubSystemEntity sub);
}
