package com.hellomc.mygoogle.bean;

/**
 * Created by hellomc on 2015/12/26.
 */
public class TopicInfo {
    private String des;
    private String url;

    public TopicInfo() {
    }

    public TopicInfo(String des, String url) {
        this.des = des;
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "des='" + des + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
