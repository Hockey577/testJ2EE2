package com.zjrt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Administrator on 2018-2-6.
 */
public class LogInfoEntity {
    private long logid;
    private long userid;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date logtime;
    private int type;

    public LogInfoEntity() {
    }

    public LogInfoEntity(long logid, long userid, String username, Date logtime, int type) {
        this.logid = logid;
        this.userid = userid;
        this.username = username;
        this.logtime = logtime;
        this.type = type;
    }

    public long getLogid() {
        return logid;
    }

    public void setLogid(long logid) {
        this.logid = logid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
