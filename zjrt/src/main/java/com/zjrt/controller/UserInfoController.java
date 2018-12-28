package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.dto.ObjectResult;
import com.zjrt.entity.LogInfoEntity;
import com.zjrt.entity.UserInfoEntity;
import com.zjrt.service.LogInfoService;
import com.zjrt.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018-2-9.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
    private static Logger logger = Logger.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LogInfoService logInfoService;

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult addUser(@RequestBody UserInfoEntity userInfoEntity) {
        CommonResult commonResult = new CommonResult();
        UserInfoEntity user = userInfoService.queryByName(userInfoEntity);
        if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
            commonResult.fail();
            commonResult.setState("101");
            commonResult.setError("用户名已存在");
            return commonResult;
        }
        int i = userInfoService.addUser(userInfoEntity);
        if (i == 0) {
            return new CommonResult(true, "500", "注册失败");
        }
        return commonResult;
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"} )
    @ResponseBody
    public ObjectResult checkLogin(@RequestBody UserInfoEntity userInfoEntity){
        ObjectResult objectResult = new ObjectResult();
        UserInfoEntity userInfo = userInfoService.checkLogin(userInfoEntity);
        if (userInfo == null){
            objectResult.setState("101");
            objectResult.setError("该用户不存在");
        }else if (userInfo != null && userInfo.getPassword().equals(userInfoEntity.getPassword())){
            objectResult.setObject(userInfo);
        }else if (userInfo != null && (!userInfo.getPassword().equals(userInfoEntity.getPassword()))){
            objectResult.setState("102");
            objectResult.setError("密码错误");
        }else{
            objectResult.setSuccess(false);
            objectResult.setState("500");
        }
        LogInfoEntity log = new LogInfoEntity();
        log.setUserid(userInfo.getUserid());
        log.setUsername(userInfo.getUsername());
        Date now = new Date();
        log.setLogtime(now);
        log.setType(userInfo.getType());
        int i = logInfoService.addLog(log);
        return objectResult;
    }

    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"} )
    @ResponseBody
    public CommonResult modifyPwd(@RequestBody UserInfoEntity userInfoEntity){
        CommonResult commonResult = new CommonResult();
        UserInfoEntity user = userInfoService.queryByName(userInfoEntity);
        if (user == null){
            commonResult.setState("101");
            commonResult.setError("用户名不存在");
        }else{
            int i = userInfoService.modifyPwd(userInfoEntity);
            if (i == 0){
                commonResult.setState("500");
                commonResult.setError("修改失败");
            }
        }
        return commonResult;
    }

}
