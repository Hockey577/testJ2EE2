package com.zjrt.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zjrt.dto.StringResult;
import com.zjrt.entity.UserInfoEntity;
import com.zjrt.util.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

/**
 * 短信验证---阿里大于工具
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    static final Integer NUM = 6;

    @RequestMapping(value = "/send", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StringResult sendMessage(@RequestBody UserInfoEntity userInfo) throws ClientException, InterruptedException {
        //生成验证码：
        String smsCode = "";
        Random r = new Random(new Date().getTime());
        for (int i = 0; i < NUM; i++) {
            smsCode = smsCode + r.nextInt(10);
        }
        long phoneNum = Long.valueOf(userInfo.getPhonenum());
        SendSmsResponse response = SmsUtil.getInstance().sendSms(phoneNum, smsCode);
        if (response.getCode().equals("OK")) {
            return new StringResult(true, response.getMessage(), "验证码：" + smsCode);
        } else
            return new StringResult(false, response.getMessage(), response.getCode());
    }
}