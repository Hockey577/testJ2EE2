package com.zjrt.service.Impl;

import com.zjrt.dao.FirstPageImagesDao;
import com.zjrt.dao.UserInfoDao;
import com.zjrt.entity.FirstPageImagesEntity;
import com.zjrt.service.FirstPageImagesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018-2-14.
 */
@Service
@Transactional
public class FirstPageImagesServiceImpl implements FirstPageImagesService{
    @Autowired
    private FirstPageImagesDao firstPageImagesDao;
    private Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Override
    public List<FirstPageImagesEntity> getList() {
        List<FirstPageImagesEntity> list = firstPageImagesDao.queryByAll();
        return list;
    }

    @Override
    public int deleteById(long imageid) {
        int i = firstPageImagesDao.deleteById(imageid);
        return i;
    }

    @Override
    public FirstPageImagesEntity queryById(long imageid) {
        FirstPageImagesEntity image = firstPageImagesDao.queryById(imageid);
        return image;
    }

    @Override
    public int addImage(FirstPageImagesEntity f) {
        return firstPageImagesDao.addImage(f);
    }
}
