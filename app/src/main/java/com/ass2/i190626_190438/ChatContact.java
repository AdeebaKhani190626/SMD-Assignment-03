package com.ass2.i190626_190438;

import android.graphics.Bitmap;

public class ChatContact {

    String name;
    String msg;
    int dp;
    String time;

    public ChatContact(String name, String msg, int dp, String time) {
        this.name = name;
        this.msg = msg;
        this.dp = dp;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
