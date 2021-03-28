package cn.namelesser.domain;

import org.apache.http.client.CookieStore;

import java.lang.reflect.Array;

import java.util.ArrayList;

public class User {
    private String account;
    private String password;
    private double aimTime;
    private double nowTime;
    private int threadNum;
   ArrayList<CookieStore> cookieStores= new ArrayList<CookieStore>();




    public User(String account, String password, double aimTime,  int threadNum) {
        this.account = account;
        this.password = password;
        this.aimTime = aimTime;
        this.threadNum = threadNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAimTime() {
        return aimTime;
    }

    public void setAimTime(double aimTime) {
        this.aimTime = aimTime;
    }

    public double getNowTime() {
        return nowTime;
    }

    public void setNowTime(double nowTime) {
        this.nowTime = nowTime;
    }

    public ArrayList<CookieStore> getCookieStores() {
        return cookieStores;
    }

    public void setCookieStores(ArrayList<CookieStore> cookieStores) {
        this.cookieStores = cookieStores;
    }


    public void addNowTime(double time){
        nowTime+=time;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", aimTime=" + aimTime +
                ", nowTime=" + nowTime +
                ", cookieStores=" + cookieStores +
                '}';
    }
}
