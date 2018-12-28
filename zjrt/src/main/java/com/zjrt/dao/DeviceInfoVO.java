package com.zjrt.dao;

/**
 * Created by Administrator on 2018-2-24.
 */
public class DeviceInfoVO {
    private String deviceid;
    private String devicename;
    private String docpath;
    private String devicemap;
    private String subsystemid;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DeviceInfoVO() {
    }

    public DeviceInfoVO(String deviceid, String devicename, String docpath,
                        String devicemap, String subsystemid,int type) {
        this.deviceid = deviceid;
        this.devicename = devicename;
        this.docpath = docpath;
        this.devicemap = devicemap;
        this.subsystemid = subsystemid;
        this.type = type;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
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

    public String getDevicemap() {
        return devicemap;
    }

    public void setDevicemap(String devicemap) {
        this.devicemap = devicemap;
    }

    public String getSubsystemid() {
        return subsystemid;
    }

    public void setSubsystemid(String subsystemid) {
        this.subsystemid = subsystemid;
    }
}
