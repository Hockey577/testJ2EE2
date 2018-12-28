package com.zjrt.service;

import com.zjrt.entity.MaintainEntity;
import com.zjrt.dao.NumberVO;

import java.util.List;

/**
 * Created by Apple on 2018/2/10.
 */
public interface MaintenanceInfoService {
    /**
     * 获取维护经验列表
     *
     * @return
     */
    List<MaintainEntity> queryALL(long startRow,long pageSize);

    MaintainEntity queryById(long id);

    Integer createMaintain(MaintainEntity m);

    Integer updateMaintain(MaintainEntity m);
    Integer updateMaintainNew(MaintainEntity m);
    Integer updateMaintainNew1(MaintainEntity m);
    Integer updateMaintainNew2(MaintainEntity m);

    Integer deleteMaintain(Long id);

    int setTop(long id);
    int undoTop(long id);

    List<MaintainEntity> queryByName(MaintainEntity m);

    NumberVO countId();
}
