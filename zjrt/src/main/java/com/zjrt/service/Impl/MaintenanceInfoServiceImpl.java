package com.zjrt.service.Impl;

import com.zjrt.dao.MaintenanceInfoDao;
import com.zjrt.entity.MaintainEntity;
import com.zjrt.service.MaintenanceInfoService;
import com.zjrt.dao.NumberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Apple on 2018/2/11.
 */
@Service
@Transactional
public class MaintenanceInfoServiceImpl implements MaintenanceInfoService {

    @Autowired
    MaintenanceInfoDao maintenanceInfoDao;

    @Override
    public List<MaintainEntity> queryALL(long startRow,long pageSize) {
        return maintenanceInfoDao.queryALL(startRow,pageSize);
    }

    @Override
    public MaintainEntity queryById(long id) {
        return maintenanceInfoDao.queryById(id);
    }

    @Override
    public Integer createMaintain(MaintainEntity m) {
        return maintenanceInfoDao.createMaintain(m);
    }

    @Override
    public Integer updateMaintain(MaintainEntity m) {
        return maintenanceInfoDao.updateMaintain(m);
    }

    @Override
    public Integer updateMaintainNew(MaintainEntity m) {
        return maintenanceInfoDao.updateMaintainNew(m);
    }

    @Override
    public Integer updateMaintainNew1(MaintainEntity m) {
        return maintenanceInfoDao.updateMaintainNew1(m);
    }

    @Override
    public Integer updateMaintainNew2(MaintainEntity m) {
        return maintenanceInfoDao.updateMaintainNew2(m);
    }

    @Override
    public Integer deleteMaintain(Long id) {
        return maintenanceInfoDao.deleteMaintain(id);
    }

    @Override
    public int setTop(long id) {
        //置顶，修改置顶值即可
        return maintenanceInfoDao.setTop(id);
    }

    @Override
    public int undoTop(long id) {
        return maintenanceInfoDao.undoTop(id);
    }

    @Override
    public List<MaintainEntity> queryByName(MaintainEntity m) {
        return maintenanceInfoDao.queryByName(m);
    }

    @Override
    public NumberVO countId() {
        return maintenanceInfoDao.countId();
    }
}
