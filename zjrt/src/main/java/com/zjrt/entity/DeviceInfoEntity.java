package com.zjrt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备信息
 * Created by Administrator on 2018-2-7.
 */
@Data
//data在我这里不管用所以我又加了get&set
public class DeviceInfoEntity implements Serializable {
    private static final long serialVersionUID = 668371119499902495L;
    private Long deviceid;//设备id
    private String devicename;//设备名称
    //需要做文件名字处理
    private String docpath;//设备文档路径
    private String originaldocname;//原设备文档名字
    //需要做文件名字处理
    private String devicemap;//设备机柜地图路径
    private String originaldevicename;//原设备机柜地图文档名字
    private long subsystemid;//子系统id
    private long number;
    private int type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDocpath() {
        return docpath;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

    public String getOriginaldocname() {
        return originaldocname;
    }

    public void setOriginaldocname(String originaldocname) {
        this.originaldocname = originaldocname;
    }

    public String getDevicemap() {
        return devicemap;
    }

    public void setDevicemap(String devicemap) {
        this.devicemap = devicemap;
    }

    public String getOriginaldevicename() {
        return originaldevicename;
    }

    public void setOriginaldevicename(String originaldevicename) {
        this.originaldevicename = originaldevicename;
    }

    public long getSubsystemid() {
        return subsystemid;
    }

    public void setSubsystemid(long subsystemid) {
        this.subsystemid = subsystemid;
    }

    public DeviceInfoEntity(String devicename) {
        this.devicename = devicename;
    }

    public DeviceInfoEntity() {
    }

    public DeviceInfoEntity(long deviceid, String devicename, String docpath,
                            String devicemap, long subsystemid,int type) {
        this.deviceid = deviceid;
        this.devicename = devicename;
        this.docpath = docpath;
        this.devicemap = devicemap;
        this.subsystemid = subsystemid;
        this.type = type;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
