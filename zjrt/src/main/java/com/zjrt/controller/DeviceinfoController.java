package com.zjrt.controller;

import com.zjrt.dto.*;
import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.service.DeviceinfoService;
import com.zjrt.util.FileUtils;
import com.zjrt.dao.DeviceInfoVO;
import com.zjrt.dao.DeviceVO;
import com.zjrt.dao.LimitVO;
import com.zjrt.dao.NumberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Created by Apple on 2018/2/19.
 */
@Controller
@RequestMapping("/device")
public class DeviceinfoController {
    @Autowired
    private DeviceinfoService deviceinfoService;

    @RequestMapping(value = "/addDevice", method = {RequestMethod.POST})
    @ResponseBody
    public StringResult addDevice(DeviceInfoVO d, @RequestParam("file")MultipartFile[] files) {
        DeviceInfoEntity device = new DeviceInfoEntity();
        FileUtils fileUtils = FileUtils.getInstance();
        int i = 0;
        for (MultipartFile file : files) {
            i++;
            String uuid = UUID.randomUUID().toString();
            // 截取文件的扩展名(如.jpg)
            String oriName = file.getOriginalFilename();
            String extName = oriName.substring(oriName.lastIndexOf("."));
            //文件上传列表中第一个文件必须要求为设备位置文档，第二个文件必须是设备描述文档
            if (i == 1) {
                //传docpath
                if (d.getType() == 3){
                    device.setOriginaldocname(oriName);
                    device.setDocpath(uuid + extName);
                }else if (d.getType() == 4){
                    //传devicemap
                    device.setOriginaldevicename(oriName);
                    device.setDevicemap(uuid + extName);
                }else {
                    device.setOriginaldocname(oriName);
                    device.setDocpath(uuid + extName);
                }
            } else {
                //设置路径文档 原名称
                device.setOriginaldevicename(oriName);
                device.setDevicemap(uuid + extName);
            }
            fileUtils.writeFile(file,uuid+extName);
        }

        //强转：
        String subsystemid = d.getSubsystemid();
        long subsystemidNew = Long.valueOf(subsystemid);

        //赋值：
        device.setDevicename(d.getDevicename());
        device.setSubsystemid(subsystemidNew);

        if (d.getDeviceid() == null) {
            return deviceinfoService.createDeviceinfo(device);
        } else {
            String id = d.getDeviceid();
            long idNew = Long.valueOf(id);
            device.setDeviceid(idNew);
            if (device.getDevicemap() != null && device.getDocpath() !=null){
                return deviceinfoService.updateDeviceinfo(device);
            }else if (device.getDevicemap() == null && device.getDocpath() != null){
                return deviceinfoService.updateDeviceinfoNew1(device);
            }else if (device.getDevicemap() != null && device.getDocpath() == null){
                return deviceinfoService.updateDeviceinfoNew2(device);
            }else {
                return deviceinfoService.updateDeviceinfoNew(device);
            }
        }
    }

    @RequestMapping(value = "/queryByTerm",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult<DeviceInfoEntity> queryByTerm(@RequestBody DeviceVO d){
        ListResult<DeviceInfoEntity> result = new ListResult<>();
        List<DeviceInfoEntity> list = deviceinfoService.queryByTerm(d.getDevicename(),
                d.getSystemname(),d.getSubsystemname());
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/queryAll",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult<DeviceInfoEntity> queryAll(@RequestBody LimitVO l){
        ListResult<DeviceInfoEntity> result = new ListResult<>();
        List<DeviceInfoEntity> list = deviceinfoService.queryALL(l.getStartRow(),l.getPageSize());
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/countId",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ObjectResult countId(){
        ObjectResult result = new ObjectResult();
        NumberVO i = deviceinfoService.countId();
        result.setObject(i);
        return result;
    }

    @RequestMapping(value = "/deleteById",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StringResult deleteById(@RequestBody DeviceInfoEntity d){
        return deviceinfoService.deleteDeviceinfo(d.getDeviceid());
    }


}
