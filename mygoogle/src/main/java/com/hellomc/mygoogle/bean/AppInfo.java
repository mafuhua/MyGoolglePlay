package com.hellomc.mygoogle.bean;

import java.util.List;

/**
 * Created by hellomc on 2015/12/26.
 */
public class AppInfo {

    /**
     * id : 1631309
     * name : 今日头条
     * packageName : com.ss.android.article.news
     * iconUrl : app/com.ss.android.article.news/icon.jpg
     * stars : 4.5
     * size : 5599469
     * downloadUrl : app/com.ss.android.article.news/com.ss.android.article.news.apk
     * des : ★用户量过亿的新闻阅读客户端★占领AppStore新闻类榜首15个月★每天用
     */

    private long id;
    private String name;
    private String packageName;
    private String iconUrl;
    private float stars;
    private long size;
    private String downloadUrl;
    private String des;
    /**
     * 详情信息
     */
    private String author;//作者
    private String date;//发布日期
    private String downloadNum;//下载量
    private String version;//版本
    /**
     * 安全信息
     */
    private List<String> safeDesList;//安全信息描述
    private List<Integer> safeDesColorList;//安全信息描述字体颜色
    private List<String> safeDesUrlList;//安全信息描述对应图片url
    private List<String> safeUrlList;//安全信息对应图片url
    //截屏信息
    private List<String> screenList;//安全信息对应图片url
    public AppInfo(String des, String downloadUrl, float stars, int size, String packageName, String name, int id, String iconUrl) {
        this.des = des;
        this.downloadUrl = downloadUrl;
        this.stars = stars;
        this.size = size;
        this.packageName = packageName;
        this.name = name;
        this.id = id;
        this.iconUrl = iconUrl;
    }
    public AppInfo(long id, String name, String packageName, String iconUrl, float stars, long size,
                   String downloadUrl, String des, String author, String date, String downloadNum, String version,
                   List<String> safeDesList, List<Integer> safeDesColorList, List<String> safeDesUrlList,
                   List<String> safeUrlList, List<String> screenList) {
        super();
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.iconUrl = iconUrl;
        this.stars = stars;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
        this.author = author;
        this.date = date;
        this.downloadNum = downloadNum;
        this.version = version;
        this.safeDesList = safeDesList;
        this.safeDesColorList = safeDesColorList;
        this.safeDesUrlList = safeDesUrlList;
        this.safeUrlList = safeUrlList;
        this.screenList = screenList;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "des='" + des + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getSafeDesList() {
        return safeDesList;
    }

    public void setSafeDesList(List<String> safeDesList) {
        this.safeDesList = safeDesList;
    }

    public List<Integer> getSafeDesColorList() {
        return safeDesColorList;
    }

    public void setSafeDesColorList(List<Integer> safeDesColorList) {
        this.safeDesColorList = safeDesColorList;
    }

    public List<String> getSafeDesUrlList() {
        return safeDesUrlList;
    }

    public void setSafeDesUrlList(List<String> safeDesUrlList) {
        this.safeDesUrlList = safeDesUrlList;
    }

    public List<String> getSafeUrlList() {
        return safeUrlList;
    }

    public void setSafeUrlList(List<String> safeUrlList) {
        this.safeUrlList = safeUrlList;
    }

    public List<String> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<String> screenList) {
        this.screenList = screenList;
    }

}
