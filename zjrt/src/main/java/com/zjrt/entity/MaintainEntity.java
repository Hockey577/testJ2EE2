package com.zjrt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 集群设备维护经验
 * Created by Administrator on 2018-2-7.
 */
public class MaintainEntity {
    private long maintainid;
    private String title;
    private String devicename;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date addtime;
    private String username;
    private String description;
    private String solution;
    private int type;
    private String photo1;//问题图片
    private String photo2;//解决图片


    public MaintainEntity() {
    }

    public MaintainEntity(long maintainid, String title, String devicename,
                          Date addtime, String username, String description,
                          String solution, int type, String photo1, String photo2) {
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
    }

    @Override
    public String toString() {
        return "MaintainEntity{" +
                "maintainid=" + maintainid +
                ", title='" + title + '\'' +
                ", devicename='" + devicename + '\'' +
                ", addtime=" + addtime +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", solution='" + solution + '\'' +
                ", type=" + type +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                '}';
    }

    //    private MaintainEntity(Builder builder) {
//        this.maintainid = builder.maintainid;
//        this.title = builder.title;
//        this.devicename = builder.devicename;
//        this.addtime = builder.addtime;
//        this.username = builder.username;
//        this.decription = builder.decription;
//        this.solution = builder.solution;
//        this.type = builder.type;
//    }

//    //使用建造者模式替代构造函数的方式
//    public static class Builder {
//        private long maintainid;
//        private String title;
//        private String devicename;
//        private Date addtime;
//        private String username;
//        private String decription;
//        private String solution;
//        private int type;
//
//        protected Builder maintainid(long maintainid) {
//            this.maintainid = maintainid;
//            return this;
//        }
//
//        protected Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//
//        protected Builder devicename(String devicename) {
//            this.devicename = devicename;
//            return this;
//        }
//
//        protected Builder addtime(Date addtime) {
//            this.addtime = addtime;
//            return this;
//        }
//
//        protected Builder username(String username) {
//            this.username = username;
//            return this;
//        }
//
//        protected Builder decription(String decription) {
//            this.decription = decription;
//            return this;
//        }
//
//        protected Builder solution(String solution) {
//            this.solution = solution;
//            return this;
//        }
//
//        protected Builder type(int type) {
//            this.type = type;
//            return this;
//        }

//        protected MaintainEntity build() {
//            return new MaintainEntity(this);
//        }
//    }

    public long getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(long maintainid) {
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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
