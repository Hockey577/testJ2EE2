package com.zjrt.entity;

/**
 * Created by Administrator on 2018-2-6.
 */
public class ClusterSystemEntity {
    private long systemid;
    private String systemname;
    private String originalSystemName;

    public String getOriginalSystemName() {
        return originalSystemName;
    }

    public void setOriginalSystemName(String originalSystemName) {
        this.originalSystemName = originalSystemName;
    }

    private String systemhtmlpath;

    public ClusterSystemEntity() {
    }

    private String systemdescription;

    public ClusterSystemEntity(long systemid, String systemname, String systemhtmlpath, String systemdescription) {
        this.systemid = systemid;
        this.systemname = systemname;
        this.systemhtmlpath = systemhtmlpath;
        this.systemdescription = systemdescription;
    }

    public long getSystemid() {
        return systemid;
    }

    public void setSystemid(long systemid) {
        this.systemid = systemid;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getSystemhtmlpath() {
        return systemhtmlpath;
    }

    public void setSystemhtmlpath(String systemhtmlpath) {
        this.systemhtmlpath = systemhtmlpath;
    }

    public String getSystemdescription() {
        return systemdescription;
    }

    public void setSystemdescription(String systemdescription) {
        this.systemdescription = systemdescription;
    }
}
