package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.dto.ListResult;
import com.zjrt.entity.SubSystemEntity;
import com.zjrt.service.SubSystemService;
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
@RequestMapping("/sub")
public class SubSystemController {
    @Autowired
    private SubSystemService subSystemService;

    @RequestMapping(value = "/getByCluster", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult getByCluster(@RequestBody SubSystemEntity s) {
        ListResult<SubSystemEntity> listResult;
        List<SubSystemEntity> list = subSystemService.queryByCluster(s.getSystemid());
        if (list == null) {
            listResult = new ListResult<>(false, "500", "无");
        } else {
            listResult = new ListResult<>(true, list, "200");
        }
        return listResult;
    }

    /**
     * 添加子系统
     * 入参：子系统名字、集群id、子系统的Pdf、按钮id
     * 无论添加还是修改，都是对应写死的buttonid
     *
     * @param sub
     * @param file
     * @return
     */
    @RequestMapping(value = "/addSub", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult addSub(SubSystemEntity sub, @RequestParam("file") MultipartFile file) {
        CommonResult commonResult = new CommonResult();
        //设置file路径
        FileUtils fileUtils = FileUtils.getInstance();
        String uuid = UUID.randomUUID().toString();
        String oriName = file.getOriginalFilename();
        String extName = oriName.substring(oriName.lastIndexOf("."));
        sub.setOriginalSubSystemName(oriName);
        sub.setSubsystemfilepath(uuid + extName);
        fileUtils.writeFile(file, uuid + extName);

        if (sub.getSubsystemid() == 0){
            int i = subSystemService.addSub(sub);
            if (i == 1)
                return commonResult;
            else
                return new CommonResult(false,"500","null");
        }else {
            int i = subSystemService.updateSub(sub);
            if(i == 1){
                return commonResult;
            }else {
                return new CommonResult(false,"500","null");
            }
        }
    }

    @RequestMapping(value = "/deleteSub", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult deleteSub(@RequestBody SubSystemEntity sub) {
        CommonResult commonResult = new CommonResult();
        int i = subSystemService.deleteById(sub);
        if (i != 1){
            commonResult.setSuccess(false);
            commonResult.setState("101");
            commonResult.setError("删除错误");
            return commonResult;
        }else
            return commonResult;
    }
}
