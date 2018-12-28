package com.zjrt.dao;

import com.zjrt.entity.MaintainEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 维护经验
 * Created by Apple on 2018/2/10.
 */
public interface MaintenanceInfoDao {

    /**
     * 获取维护经验列表
     *
     * @return
     */
    List<MaintainEntity> queryALL(@Param("startRow") long startRow,
                                  @Param("pageSize") long pageSize);

    MaintainEntity queryById(long id);

    Integer createMaintain(MaintainEntity m);

    Integer updateMaintain(MaintainEntity m);

    //两张图片都不改
    Integer updateMaintainNew(MaintainEntity m);
    //改第一张
    Integer updateMaintainNew1(MaintainEntity m);
    //改第二张
    Integer updateMaintainNew2(MaintainEntity m);

    Integer deleteMaintain(Long id);

    /**
     * 置顶
     * @param id
     * @return
     */
    int setTop(long id);
    int undoTop(long id);

    /**
     * 根据名字模糊查询
     * @param m
     * @return
     */
    List<MaintainEntity> queryByName(MaintainEntity m);

    /**
     * 计数---设备文档数目
     * @return
     */
    NumberVO countId();


}
