package com.zjrt.dao;

/**
 * Created by Administrator on 2018-2-23.
 */
public class DeviceVO {
    private String devicename;
    private String systemname;
    private String subsystemname;

    public DeviceVO() {
    }

    public DeviceVO(String devicename, String systemname, String subsystemname) {
        this.devicename = devicename;
        this.systemname = systemname;
        this.subsystemname = subsystemname;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getSubsystemname() {
        return subsystemname;
    }

    public void setSubsystemname(String subsystemname) {
        this.subsystemname = subsystemname;
    }
}
