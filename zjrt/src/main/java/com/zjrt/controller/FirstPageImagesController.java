package com.zjrt.controller;

import com.zjrt.dto.CommonResult;
import com.zjrt.dto.ListResult;
import com.zjrt.entity.FirstPageImagesEntity;
import com.zjrt.service.FirstPageImagesService;
import com.zjrt.service.UserInfoService;
import com.zjrt.util.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static com.zjrt.config.InstantiationTracingBeanPostProcessor.aa;

/**
 * Created by Administrator on 2018-2-14.
 */
@Controller
@RequestMapping("/images")
public class FirstPageImagesController {
    private static Logger logger = Logger.getLogger(UserInfoController.class);
    @Autowired
    private FirstPageImagesService firstPageImagesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ListResult<FirstPageImagesEntity> getList(){
        ListResult<FirstPageImagesEntity> listResult;
        List<FirstPageImagesEntity> list = firstPageImagesService.getList();
        if (list == null){
            listResult = new ListResult<FirstPageImagesEntity>(false,"500","获取失败");
        }else {
            listResult = new ListResult<FirstPageImagesEntity>(true, list, "200");
        }
        return listResult;
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult deleteById(@RequestBody FirstPageImagesEntity image){
        CommonResult commonResult = new CommonResult();
        FirstPageImagesEntity imagesEntity = firstPageImagesService.queryById(image.getImageid());
        if (imagesEntity == null){
            commonResult.setSuccess(true);
            commonResult.setState("101");
            commonResult.setError("图片不存在");
        }else {
            int i = firstPageImagesService.deleteById(image.getImageid());
            if (i == 0) {
                commonResult.setSuccess(false);
                commonResult.setState("500");
                commonResult.setError("删除失败");
            }
        }
        return commonResult;
    }

    @RequestMapping(value = "/addImage",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult addImage(FirstPageImagesEntity image,@RequestParam("file")MultipartFile file){
        CommonResult commonResult = new CommonResult();
        //设置图片路径
        FileUtils fileUtils = FileUtils.getInstance();
        String uuid = UUID.randomUUID().toString();
        String oriName = file.getOriginalFilename();
        String extName = oriName.substring(oriName.lastIndexOf("."));
        image.setOriginalImageName(oriName);
        image.setImagepath(uuid + extName);
        fileUtils.writeFile(file,uuid+extName);

        //添加图片
        int i = firstPageImagesService.addImage(image);
        if (i == 0) {
            commonResult.setSuccess(false);
            commonResult.setState("500");
            commonResult.setError("添加失败");
        }
        return commonResult;
    }
}
