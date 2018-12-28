package com.zjrt.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Administrator on 2018-2-23.
 */
public class MaintainVO {
    private String maintainid;
    private String title;
    private String devicename;
    private String addtime;
    private String username;
    private String description;
    private String solution;
    private String type;
    private String photo1;//问题图片
    private String photo2;//解决图片
    private int type1;

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public MaintainVO(String maintainid, String title, String devicename,
                      String addtime, String username, String description,
                      String solution, String type, String photo1, String photo2,int type1) {
        this.maintainid = maintainid;
        this.title = title;
        this.devicename = devicename;
        this.addtime = addtime;
        this.username = username;
        this.description = description;
        this.solution = solution;
        this.type = type;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.type1 = type1;
    }

    public MaintainVO() {
    }

    public String getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(String maintainid) {
        this.maintainid = maintainid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }
}
