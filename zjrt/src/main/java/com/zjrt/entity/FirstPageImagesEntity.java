package com.zjrt.entity;

/**
 * Created by Administrator on 2018-2-6.
 */
public class FirstPageImagesEntity {
    private long imageid;
    private String imagename;
    private String imagepath;
    private String originalImageName;//原图片名字

    public String getOriginalImageName() {
        return originalImageName;
    }

    public void setOriginalImageName(String originalImageName) {
        this.originalImageName = originalImageName;
    }

    private String description;

    public FirstPageImagesEntity() {
    }

    public FirstPageImagesEntity(long imageid) {
        this.imageid = imageid;
    }

    public FirstPageImagesEntity(long imageid, String imagename, String imagepath, String description) {
        this.imageid = imageid;
        this.imagename = imagename;
        this.imagepath = imagepath;
        this.description = description;
    }

    public FirstPageImagesEntity(String imagename, String imagepath, String description) {
        this.imagename = imagename;
        this.imagepath = imagepath;
        this.description = description;
    }

    public long getImageid() {
        return imageid;
    }

    public void setImageid(long imageid) {
        this.imageid = imageid;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
