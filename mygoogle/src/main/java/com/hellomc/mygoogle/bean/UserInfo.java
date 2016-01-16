package com.hellomc.mygoogle.bean;

public class UserInfo {
    private String name;// 登陆名
    private String email;// 邮箱
    private String url;// 头像的地址
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public UserInfo(String name, String email, String url) {
        super();
        this.name = name;
        this.email = email;
        this.url = url;
    }
    
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString() {
        return "UserInfo [name=" + name + ", email=" + email + ", url=" + url + "]";
    }
    
}