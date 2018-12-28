package com.zjrt.dao;

import com.zjrt.entity.DeviceInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备管理
 * Created by Apple on 2018/2/11.
 */
public interface DeviceInfoDao {
    /**
     * 获取设备文档列表
     *
     * @return
     */
    List<DeviceInfoEntity> queryALL(@Param("startRow") long startRow,
                                    @Param("pageSize") long pageSize);

    Integer createDeviceInfo(DeviceInfoEntity d);

    Integer updateDeviceInfo(DeviceInfoEntity d);

    //都不修改
    Integer updateDeviceInfoNew(DeviceInfoEntity d);
    //修改docpath
    Integer updateDeviceInfoNew1(DeviceInfoEntity d);
    //修改devicepath
    Integer updateDeviceInfoNew2(DeviceInfoEntity d);

    Integer deleteDeviceInfo(Long id);

    /**
     * 条件查询
     * @param devicename 设备名称
     * @param systemname 集群名称
     * @param subsystemname 子系统名称
     * @return
     */
    List<DeviceInfoEntity> queryByTerm(@Param("devicename") String devicename,
                                       @Param("systemname") String systemname,
                                       @Param("subsystemname") String subsystemname);

    /**
     * 计数---设备文档数目
     * @return
     */
    NumberVO countId();
}
