package com.hellomc.mygoogle.protocol;

import com.hellomc.mygoogle.bean.TopicInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellomc on 2015/12/26.
 */
public class TopicProtocol extends BaseProtocol<List<TopicInfo>> {
    List<TopicInfo> topicInfos = new ArrayList<>();

    @Override
    public List<TopicInfo> parseJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String des = jsonObject.getString("des");
                String url = jsonObject.getString("url");
                TopicInfo topicInfo = new TopicInfo(des, url);
                topicInfos.add(topicInfo);
            }
            return topicInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected String getKey() {
        return "subject";
    }
}
