package com.hellomc.mygoogle.protocol;

import android.text.TextUtils;
import android.util.Log;

import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.utils.FileUtils;
import com.lidroid.xutils.util.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by hellomc on 2015/12/26.
 */
public abstract class BaseProtocol<T> {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private StringWriter stringWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public String getParams() {
        return "";
    }

    public T load(int index) {
        String json = readCache(index);
        if (TextUtils.isEmpty(json)) {
            json = loadServer(index);
            Log.d("BaseProtocol", "index:" + index);
            if (!TextUtils.isEmpty(json)) {
                saveCache(json, index);
            }
        } else {
            //Log.d("HomeProtocol", "读取缓存");
        }
        if (!TextUtils.isEmpty(json)) {
            T appInfos = parseJson(json);
            return appInfos;
        }
        return null;
    }

    public abstract T parseJson(String json);

    private void saveCache(String json, int index) {
        File dir = FileUtils.getFileCacheJson();
        File file = new File(dir, getKey() + "_" + index+getParams());
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(fileWriter);
        }

    }

    protected abstract String getKey();


    private String loadServer(int index) {
        /*  HttpUtils httpUtils = new HttpUtils();
          ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET, "http://127.0.0.1:8090/home?index=" + index);
          readString = responseStream.readString();*/
        HttpHelper.HttpResult result = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index + getParams());
        String resultString = result.getString();

        return resultString;


    }

    private String readCache(int index) {
        File dir = FileUtils.getFileCacheJson();
        File file = new File(dir, getKey() + "_" + index);
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String temp = "";
            stringWriter = new StringWriter();
            while ((temp = bufferedReader.readLine()) != null) {
                stringWriter.write(temp);
            }
            return stringWriter.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(stringWriter);
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(fileReader);
        }

    }
}
