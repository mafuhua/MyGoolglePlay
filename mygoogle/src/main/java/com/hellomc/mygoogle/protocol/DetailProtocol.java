package com.hellomc.mygoogle.protocol;

import com.hellomc.mygoogle.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellomc on 2015/12/28.
 */
public class DetailProtocol extends BaseProtocol<AppInfo> {
    //包名
    private String packageName;

    public DetailProtocol(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getKey() {
        return "detail";
    }

    @Override
    public String getParams() {
        return "&packageName=" + packageName;
    }

    @Override
    public AppInfo parseJson(String json) {
        try {
            JSONObject appObject = new JSONObject(json);
            /**
             * "id": 1525490, "name": "有缘网", "packageName":
             * "com.youyuan.yyhl", "iconUrl":
             * "app/com.youyuan.yyhl/icon.jpg", "stars": 4, "size": 3876203,
             * "downloadUrl": "app/com.youyuan.yyhl/com.youyuan.yyhl.apk",
             * "des": "产品介绍：有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、"
             */
            long id = appObject.getLong("id");
            String name = appObject.getString("name");
            String packageName = appObject.getString("packageName");
            String iconUrl = appObject.getString("iconUrl");
            float stars = (float) appObject.getDouble("stars");
            long size = appObject.getLong("size");
            String downloadUrl = appObject.getString("downloadUrl");
            String des = appObject.getString("des");
            /**
             * 详情信息解析
             private String author;//作者
             private String date;//发布日期
             private String downloadNum;//下载量
             private String version;//版本
             private List<String> safeDesList;//安全信息描述
             private List<Integer> safeDesColorList;//安全信息描述字体颜色
             private List<String> safeDesUrlList;//安全信息描述对应图片url
             private List<String> safeUrlList;//安全信息对应图片url
             //截屏信息
             private List<String> screenList;
             */
            String author = appObject.getString("author");
            String date = appObject.getString("date");
            String downloadNum = appObject.getString("downloadNum");
            String version = appObject.getString("version");
            //安全信息
            List<String> safeDesList = new ArrayList<String>();//安全信息描述
            List<Integer> safeDesColorList = new ArrayList<Integer>();//安全信息描述字体颜色
            List<String> safeDesUrlList = new ArrayList<String>();//安全信息描述对应图片url
            List<String> safeUrlList = new ArrayList<String>();//安全信息对应图片url

            JSONArray safeArray = appObject.getJSONArray("safe");
            for (int i = 0; i < safeArray.length(); i++) {
                JSONObject safeObject = safeArray.getJSONObject(i);
                String safeDes = safeObject.getString("safeDes");
                safeDesList.add(safeDes);

                int safeDesColor = safeObject.getInt("safeDesColor");
                safeDesColorList.add(safeDesColor);

                String safeDesUrl = safeObject.getString("safeDesUrl");
                safeDesUrlList.add(safeDesUrl);


                String safeUrl = safeObject.getString("safeUrl");
                safeUrlList.add(safeUrl);

            }

            //截屏信息
            List<String> screenList = new ArrayList<String>();
            JSONArray screenArray = appObject.getJSONArray("screen");
            for (int i = 0; i < screenArray.length(); i++) {
                String screenUrl = screenArray.getString(i);
                screenList.add(screenUrl);
            }

            AppInfo appInfo = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des, author, date,
                    downloadNum, version, safeDesList, safeDesColorList, safeDesUrlList, safeUrlList, screenList);
            return appInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
