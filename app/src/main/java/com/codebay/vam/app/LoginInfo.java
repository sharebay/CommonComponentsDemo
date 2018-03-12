package com.codebay.vam.app;

import com.codebay.vam.bean.User;

import java.util.Date;

/**
 * Created by RuanJian-GuoYong on 2018/3/10.
 * 参考 https://www.cnblogs.com/dongyu666/p/6971783.html
 */

public class LoginInfo {
    private static volatile LoginInfo instance;

    //params
    private Date loginTime;
    private User currUser;


    private LoginInfo() {
    }

    public static LoginInfo getInstance() {
        if (instance == null) {
            synchronized (LoginInfo.class) {
                if (instance == null) {
                    instance = new LoginInfo();
                }
            }
        }
        return instance;
    }

    public void setUser(User user){
        this.currUser = user;
    }
    public User getCurrUser(){
        return currUser;
    }

    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }
    public Date getLoginTime(){
        return loginTime;
    }
}
