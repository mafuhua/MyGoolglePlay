package com.hellomc.mygoogle.protocol;

import com.hellomc.mygoogle.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellomc on 2015/12/27.
 */
public class AppProtocol extends BaseProtocol<List<AppInfo>> {


    @Override
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
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
        return "app";
    }
}
