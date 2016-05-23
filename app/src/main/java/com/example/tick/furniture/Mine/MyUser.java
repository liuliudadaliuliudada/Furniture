package com.example.tick.furniture.Mine;

import cn.bmob.v3.BmobUser;

/**
 * Created by Tick on 2016/5/18.
 */
public class MyUser extends BmobUser {
    Boolean Sex;//性别
    String Nick;//昵称

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public Boolean getSex() {
        return Sex;
    }

    public void setSex(Boolean sex) {
        Sex = sex;
    }
}
