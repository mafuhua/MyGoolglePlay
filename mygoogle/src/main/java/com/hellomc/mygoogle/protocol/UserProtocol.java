package com.hellomc.mygoogle.protocol;

import com.hellomc.mygoogle.bean.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hellomc on 2015/12/28.
 */
public class UserProtocol extends BaseProtocol{
    @Override
    public Object parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String url = jsonObject.getString("url");
            UserInfo userInfo = new UserInfo(name, email, url);
            return userInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected String getKey() {
        return "user";
    }
}
