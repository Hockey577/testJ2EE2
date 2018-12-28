package com.zjrt.entity;

/**
 * Created by Administrator on 2018-2-7.
 */
public class SubSystemEntity {
    private long subsystemid;
    private String subsystemname;
    private String originalSubSystemName;
    private String subsystemfilepath;
    private long systemid;
    private long buttonid;
    private int type;

    public SubSystemEntity() {
    }

    public SubSystemEntity(long subsystemid, String subsystemname,
                           String subsystemfilepath, long systemid,long buttonid,int type) {
        this.subsystemid = subsystemid;
        this.subsystemname = subsystemname;
        this.subsystemfilepath = subsystemfilepath;
        this.systemid = systemid;
        this.buttonid = buttonid;
        this.type = type;
    }

    public long getSubsystemid() {
        return subsystemid;
    }

    public void setSubsystemid(long subsystemid) {
        this.subsystemid = subsystemid;
    }

    public String getSubsystemname() {
        return subsystemname;
    }

    public void setSubsystemname(String subsystemname) {
        this.subsystemname = subsystemname;
    }

    public String getSubsystemfilepath() {
        return subsystemfilepath;
    }

    public void setSubsystemfilepath(String subsystemfilepath) {
        this.subsystemfilepath = subsystemfilepath;
    }

    public long getSystemid() {
        return systemid;
    }

    public void setSystemid(long systemid) {
        this.systemid = systemid;
    }

    public String getOriginalSubSystemName() {
        return originalSubSystemName;
    }

    public long getButtonid() {
        return buttonid;
    }

    public void setButtonid(long buttonid) {
        this.buttonid = buttonid;
    }

    public void setOriginalSubSystemName(String originalSubSystemName) {
        this.originalSubSystemName = originalSubSystemName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
