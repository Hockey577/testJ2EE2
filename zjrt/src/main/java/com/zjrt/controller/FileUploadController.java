package com.zjrt.controller;

import com.zjrt.dto.StringResult;
import com.zjrt.entity.DeviceInfoEntity;
import com.zjrt.service.DeviceinfoService;
import com.zjrt.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Created by Apple on 2018/2/12.
 */
@Controller
@RequestMapping("/update")
public class FileUploadController {

    @Autowired
    DeviceinfoService deviceinfoService;

    /**
     * 文件上传方法，多个文件
     * @param d 设备描述文档
     * @param files 上传文件的列表，第一个文件为设备文档路径，第二个为设备机柜地图路径
     * @return
     */
    @RequestMapping(value = "/updateItems", method = {RequestMethod.POST})
    @ResponseBody
    public StringResult updateItems(DeviceInfoEntity d, @RequestParam("file")MultipartFile[] files) {
        // 把图片保存到图片目录下
        // 保存图片，这个图片有的时候文件名可能会重复，你保存多了会把原来的图片给覆盖掉，这就不太合适了。
        // 所以为每个文件生成一个新的文件名
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
                //设置原文件名称
                d.setOriginaldocname(oriName);
                d.setDocpath(uuid + extName);
            } else {
                //设置路径文档 原名称
                d.setOriginaldevicename(oriName);
                d.setDevicemap(uuid + extName);
            }
            fileUtils.writeFile(file,uuid+extName);
        }
        if (d.getDeviceid() == null) {
            return deviceinfoService.createDeviceinfo(d);
        } else {
            return deviceinfoService.updateDeviceinfo(d);
        }
    }
}
