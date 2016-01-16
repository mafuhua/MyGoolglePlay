package com.hellomc.mygoogle.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hellomc on 2015/12/25.
 */
public class BaseActivity extends ActionBarActivity {

    private List<Activity> activityList = new CopyOnWriteArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initActionBar();
        activityList.add(this);
    }
    public  void  exitApp(){
        for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            activity.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
    }

    public void initView() {
    }

    public void initData() {

    }

    public void initActionBar() {

    }

}
