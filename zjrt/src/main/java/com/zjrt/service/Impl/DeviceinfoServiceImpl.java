package com.zjrt.service.Impl;

import com.zjrt.dao.DeviceInfoDao;
import com.zjrt.dto.StringResult;
import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.service.DeviceinfoService;
import com.zjrt.dao.NumberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 设备维护
 * Created by Apple on 2018/2/19.
 */
@Service
@Transactional
public class DeviceinfoServiceImpl implements DeviceinfoService {
    @Autowired
    DeviceInfoDao deviceInfoDao;

    @Override
    public List<DeviceInfoEntity> queryALL(long startRow,long pageSize) {
        return deviceInfoDao.queryALL(startRow,pageSize);
    }

    @Override
    public StringResult createDeviceinfo(DeviceInfoEntity d) {
        Integer integer = deviceInfoDao.createDeviceInfo(d);
        if (integer != 1){
            return getReturnInfo(integer,"新增错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public StringResult updateDeviceinfo(DeviceInfoEntity d) {
        Integer integer = deviceInfoDao.updateDeviceInfo(d);
        if (integer != 1){
            return getReturnInfo(integer,"修改错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public StringResult updateDeviceinfoNew(DeviceInfoEntity d) {
        Integer integer = deviceInfoDao.updateDeviceInfoNew(d);
        if (integer != 1){
            return getReturnInfo(integer,"修改错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public StringResult updateDeviceinfoNew1(DeviceInfoEntity d) {
        Integer integer = deviceInfoDao.updateDeviceInfoNew1(d);
        if (integer != 1){
            return getReturnInfo(integer,"修改错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public StringResult updateDeviceinfoNew2(DeviceInfoEntity d) {
        Integer integer = deviceInfoDao.updateDeviceInfoNew2(d);
        if (integer != 1){
            return getReturnInfo(integer,"修改错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public StringResult deleteDeviceinfo(Long id) {
        Integer integer = deviceInfoDao.deleteDeviceInfo(id);
        if (integer != 1){
            return getReturnInfo(integer,"删除错误");
        }
        return getReturnInfo(1,null);
    }

    @Override
    public List<DeviceInfoEntity> queryByTerm(String devicename, String systemname, String subsystemname) {
        return deviceInfoDao.queryByTerm(devicename,systemname,subsystemname);
    }

    @Override
    public NumberVO countId() {
        return deviceInfoDao.countId();
    }

    /**
     *  组装返回对象数据，只处理返回受影响行数为1的数据，
     *  不为1的数据需要填写错误信息
     * @param i 受影响行数
     * @param msg 错误信息
     * @return
     */
    private StringResult getReturnInfo(Integer i,String msg){
        StringResult result = new StringResult(true, "200", null);
        if (i !=1 ){
            result.setSuccess(false);
            result.setState("500");
            result.setError(msg);
        }
        return result;
    }
}
