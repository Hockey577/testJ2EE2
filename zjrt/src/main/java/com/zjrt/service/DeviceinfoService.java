package com.zjrt.service;

import com.zjrt.dto.StringResult;
import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.dao.NumberVO;

import java.util.List;

/**
 * Created by Apple on 2018/2/19.
 */
public interface DeviceinfoService {
    /**
     * 查询所有数据
     * @return
     */
    List<DeviceInfoEntity> queryALL(long startRow,long pageSize);

    /**
     * 新增设备维护信息
     * @param d
     * @return
     */
    StringResult createDeviceinfo(DeviceInfoEntity d);

    /**
     * 根据 id，更新设备信息
     * @param d
     * @return
     */
    StringResult updateDeviceinfo(DeviceInfoEntity d);
    //都不修改
    StringResult updateDeviceinfoNew(DeviceInfoEntity d);
    //修改docpath
    StringResult updateDeviceinfoNew1(DeviceInfoEntity d);
    //修改devicepath
    StringResult updateDeviceinfoNew2(DeviceInfoEntity d);

    /**
     * 根据 id，删除设备信息
     * @param id
     * @return
     */
    StringResult deleteDeviceinfo(Long id);

    List<DeviceInfoEntity> queryByTerm(String devicename,String systemname,String subsystemname);

    NumberVO countId();
}
