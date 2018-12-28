package com.zjrt.dao;

import com.zjrt.entity.FirstPageImagesEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页图片--轮播图
 * Created by Administrator on 2018-2-14.
 */
public interface FirstPageImagesDao {
    List<FirstPageImagesEntity> queryByAll();
    int deleteById(@Param(value="imageid") long imageid);
    FirstPageImagesEntity queryById(long imageid);

    /**
     * 添加首页图片
     * @param f
     * @return
     */
    int addImage(FirstPageImagesEntity f);

}
