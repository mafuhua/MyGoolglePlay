package com.hellomc.mygoogle.protocol;

import com.hellomc.mygoogle.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellomc on 2015/12/26.
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>> {

    private List<String> imageUrls;
    public List<String> getImageUrls(){
        return imageUrls;
    }
    @Override
    public List<AppInfo> parseJson(String json) {
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
        List<AppInfo> appInfos = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            imageUrls = new ArrayList<>();
            JSONArray imageArray = jsonObject.getJSONArray("picture");
            for (int i = 0; i < imageArray.length(); i++) {
                String imageUrl = imageArray.getString(i);
                imageUrls.add(imageUrl);
            }
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject appobject = jsonArray.getJSONObject(i);
                int id = appobject.getInt("id");
                String name = appobject.getString("name");
                String packageName = appobject.getString("packageName");
                String iconUrl = appobject.getString("iconUrl");
                float stars = (float) appobject.getDouble("stars");
                int size = appobject.getInt("size");
                String downloadUrl = appobject.getString("downloadUrl");
                String des = appobject.getString("des");
                AppInfo appInfo = new AppInfo(des, downloadUrl, stars, size, packageName, name, id, iconUrl);
                appInfos.add(appInfo);
            }
            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    protected String getKey() {
        return "home";

    }


}
