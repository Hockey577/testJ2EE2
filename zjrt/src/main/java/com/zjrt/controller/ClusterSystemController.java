package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.dto.ListResult;
import com.zjrt.entity.ClusterSystemEntity;
import com.zjrt.entity.FirstPageImagesEntity;
import com.zjrt.entity.MaintainEntity;
import com.zjrt.service.ClusterSystemService;
import com.zjrt.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018-2-18.
 */
@Controller
@RequestMapping("/cluster")
public class ClusterSystemController {
    @Autowired
    private ClusterSystemService clusterSystemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult getList(){
        ListResult<ClusterSystemEntity> listResult;
        List<ClusterSystemEntity> list = clusterSystemService.queryAll();
        if (list == null){
            listResult = new ListResult<>(false,"500","获取失败");
        }else {
            listResult = new ListResult<>(true, list, "200");
        }
        return listResult;
    }

    //删除集群时应该将子系统一并删除 TODO
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult deleteById(@RequestBody ClusterSystemEntity system){
        CommonResult commonResult = new CommonResult();
        int i = clusterSystemService.deleteById(system.getSystemid());
        if (i == 0){
            commonResult.setSuccess(false);
            commonResult.setState("500");
            commonResult.setError("删除失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/addCluster",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult addCluster(ClusterSystemEntity cluster,@RequestParam("file")MultipartFile file){
        CommonResult commonResult = new CommonResult();
        //设置html路径
        FileUtils fileUtils = FileUtils.getInstance();
        String uuid = UUID.randomUUID().toString();
        String oriName = file.getOriginalFilename();
        String extName = oriName.substring(oriName.lastIndexOf("."));
        cluster.setOriginalSystemName(oriName);
        cluster.setSystemhtmlpath(uuid + extName);
        fileUtils.writeFile(file,uuid+extName);

        //添加图片
        int i = clusterSystemService.addCluster(cluster);
        if (i == 0) {
            commonResult.setSuccess(false);
            commonResult.setState("500");
            commonResult.setError("添加失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/listName", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult getListName(){
        ListResult<ClusterSystemEntity> listResult;
        List<ClusterSystemEntity> list = clusterSystemService.queryAllName();
        if (list == null){
            listResult = new ListResult<>(false,"500","获取失败");
        }else {
            listResult = new ListResult<>(true, list, "200");
        }
        return listResult;
    }
}
