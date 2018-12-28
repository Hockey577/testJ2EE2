package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.dto.ListResult;
import com.zjrt.dto.ObjectResult;
import com.zjrt.entity.MaintainEntity;
import com.zjrt.service.MaintenanceInfoService;
import com.zjrt.util.FileUtils;
import com.zjrt.dao.LimitVO;
import com.zjrt.dao.MaintainVO;
import com.zjrt.dao.NumberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018-2-19.
 */
@Controller
@RequestMapping("/maintain")
public class MaintenanceInfoController {
    @Autowired
    private MaintenanceInfoService maintenanceInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult<MaintainEntity> queryAll(@RequestBody LimitVO l) {
        ListResult<MaintainEntity> listResult;
        List<MaintainEntity> list = maintenanceInfoService.queryALL(l.getStartRow(),l.getPageSize());
        if (list == null) {
            listResult = new ListResult<>(false, "500", "获取失败");
        } else {
            listResult = new ListResult<>(true, list, "200");
        }
        return listResult;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult deleteById(@RequestBody MaintainEntity maintain) {
        CommonResult commonResult = new CommonResult();
        MaintainEntity maintainEntity = maintenanceInfoService.queryById(maintain.getMaintainid());
        if (maintainEntity == null) {
            commonResult.setSuccess(true);
            commonResult.setState("101");
            commonResult.setError("维护经验不存在");
        } else {
            int i = maintenanceInfoService.deleteMaintain(maintain.getMaintainid());
            if (i == 0) {
                commonResult.setSuccess(false);
                commonResult.setState("500");
                commonResult.setError("删除失败");
            }
        }
        return commonResult;
    }

    @RequestMapping(value = "/setTop", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult setTop(@RequestBody MaintainEntity maintain) {
        CommonResult commonResult = new CommonResult();
        int i = maintenanceInfoService.setTop(maintain.getMaintainid());
        if (i == 0) {
            commonResult.setSuccess(false);
            commonResult.setState("500");
            commonResult.setError("置顶失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/undoTop", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult undoTop(@RequestBody MaintainEntity maintain) {
        CommonResult commonResult = new CommonResult();
        int i = maintenanceInfoService.undoTop(maintain.getMaintainid());
        if (i == 0) {
            commonResult.setSuccess(false);
            commonResult.setState("500");
            commonResult.setError("取消置顶失败");
        }
        return commonResult;
    }

    /**
     * 入参有：标题、设备名称、添加时间、维护人员、
     * 设备问题详情、处理方案、（两张图片）
     *
     * @param m
     * @param files
     * @return
     */
    @RequestMapping(value = "/addMaintain", method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult addMaintain(MaintainVO m, @RequestParam("file") MultipartFile[] files) {
        CommonResult commonResult = new CommonResult();
        MaintainEntity mNew = new MaintainEntity();
        //对文件进行操作
        FileUtils fileUtils = FileUtils.getInstance();
        int i = 0;
        for (MultipartFile file : files) {
            i++;
            String uuid = UUID.randomUUID().toString();
            // 截取文件的扩展名(如.jpg)
            String oriName = file.getOriginalFilename();
            String extName = oriName.substring(oriName.lastIndexOf("."));
            if (i == 1) {
                //设置问题图片路径
                if (m.getType1() == 3){
                    mNew.setPhoto1(uuid + extName);
                }else if (m.getType1() == 4){
                    mNew.setPhoto2(uuid + extName);
                }else {
                    mNew.setPhoto1(uuid + extName);
                }
            } else {
                //设置解决图片路径
                mNew.setPhoto2(uuid + extName);
            }
            fileUtils.writeFile(file, uuid + extName);

        }

        //强转时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addtime = m.getAddtime();
        Date addtimeNew = new Date();
        try {
            addtimeNew = sdf.parse(addtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //强转type
        String type = m.getType();
        int typeNew = Integer.valueOf(type);

        //赋值：
        mNew.setTitle(m.getTitle());
        mNew.setDevicename(m.getDevicename());
        mNew.setAddtime(addtimeNew);
        mNew.setUsername(m.getUsername());
        mNew.setDescription(m.getDescription());
        mNew.setSolution(m.getSolution());
        mNew.setType(typeNew);

        //添加or修改
        if (m.getMaintainid() == null) {
            int a = maintenanceInfoService.createMaintain(mNew);
            if (a == 0) {
                commonResult.setSuccess(false);
                commonResult.setState("500");
                commonResult.setError("添加失败");
            }
        } else {
            long id = Long.valueOf(m.getMaintainid());
            mNew.setMaintainid(id);
            if (mNew.getPhoto1() != null && mNew.getPhoto2() != null) {
                int a = maintenanceInfoService.updateMaintain(mNew);
                if (a == 0) {
                    commonResult.setSuccess(false);
                    commonResult.setState("500");
                    commonResult.setError("修改失败");
                }
            } else if (mNew.getPhoto1() != null && mNew.getPhoto2() == null) {
                int a = maintenanceInfoService.updateMaintainNew1(mNew);
                if (a == 0) {
                    commonResult.setSuccess(false);
                    commonResult.setState("500");
                    commonResult.setError("修改失败");
                }
            } else if (mNew.getPhoto1() == null && mNew.getPhoto2() != null) {
                int a = maintenanceInfoService.updateMaintainNew2(mNew);
                if (a == 0) {
                    commonResult.setSuccess(false);
                    commonResult.setState("500");
                    commonResult.setError("修改失败");
                }
            } else {
                int a = maintenanceInfoService.updateMaintainNew(mNew);
                if (a == 0) {
                    commonResult.setSuccess(false);
                    commonResult.setState("500");
                    commonResult.setError("修改失败");
                }
            }
        }
        return commonResult;
    }

    @RequestMapping(value = "/queryByName", method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    ListResult<MaintainEntity> queryByName(@RequestBody MaintainEntity m){
        ListResult<MaintainEntity> result = new ListResult<MaintainEntity>();
        List<MaintainEntity> list = maintenanceInfoService.queryByName(m);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/countId",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ObjectResult countId(){
        ObjectResult result = new ObjectResult();
        NumberVO i = maintenanceInfoService.countId();
        result.setObject(i);
        return result;
    }
}
