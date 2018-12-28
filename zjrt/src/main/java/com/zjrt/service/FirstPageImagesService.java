package com.zjrt.service;

import com.zjrt.entity.FirstPageImagesEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-2-14.
 */
public interface FirstPageImagesService {
    /**
     * 获取所有轮播图
     * @return
     */
    List<FirstPageImagesEntity> getList();

    int deleteById(long imageid);
    FirstPageImagesEntity queryById(long imageid);

    /**
     * 添加轮播图
     * @param f
     * @return
     */
    int addImage(FirstPageImagesEntity f);
}
