package com.example.chch2.baseapp.demo.bean;

public class RedPacketUser {
    private String time;
    private String name;
    private String money;

    public RedPacketUser(String time, String name, String money) {
        this.time = time;
        this.name = name;
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
