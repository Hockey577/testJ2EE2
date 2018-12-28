package com.zjrt.service;

import com.zjrt.entity.SubSystemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-2-18.
 */
public interface SubSystemService {
    List<SubSystemEntity> queryByCluster(long systemid);
    int addSub(SubSystemEntity sub);
    SubSystemEntity queryByName(SubSystemEntity sub);
    int updateSub(SubSystemEntity sub);
    int deleteById(SubSystemEntity sub);
}
