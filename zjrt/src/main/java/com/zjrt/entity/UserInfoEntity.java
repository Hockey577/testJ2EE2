package com.zjrt.entity;

/**
 * Created by Administrator on 2018-2-6.
 */
public class UserInfoEntity {
    private long userid;
    private String username;
    private String password;
    private int type;
    private String phonenum;
    private long code;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String username, String password, int type, String phonenum) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.phonenum = phonenum;
    }

    public UserInfoEntity(long userid, String username, String password,
                          int type, String phonenum, long code) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.type = type;
        this.phonenum = phonenum;
        this.code = code;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
