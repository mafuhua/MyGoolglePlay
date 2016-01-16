package com.hellomc.mygoogle.activity;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.fragment.BaseFragment;
import com.hellomc.mygoogle.holder.DetailBottomHolder;
import com.hellomc.mygoogle.holder.DetailDesHolder;
import com.hellomc.mygoogle.holder.DetailInfoHolder;
import com.hellomc.mygoogle.holder.DetailSafeHolder;
import com.hellomc.mygoogle.holder.DetailScreenHolder;
import com.hellomc.mygoogle.protocol.DetailProtocol;
import com.hellomc.mygoogle.view.LoadingPage;


public class DetailActivity extends BaseActivity {
    private String packageName;
    private AppInfo appInfo;

    @Override
    public void initView() {
        Intent intent = getIntent();


        packageName = intent.getStringExtra("packageName");
        Log.d("DetailActivity", packageName);
        //setContentView(R.layout.activity_detail);
        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public View createSucessView() {
                return DetailActivity.this.createSucessView();
            }

            @Override
            public BaseFragment.LoadResult load() {
                return DetailActivity.this.load();
            }
        };
        setContentView(loadingPage);
        loadingPage.showView();
        loadingPage.loadAndView();

    }

    public BaseFragment.LoadResult load() {
        DetailProtocol detailProtocol = new DetailProtocol(packageName);
        appInfo = detailProtocol.load(0);
        if (appInfo == null) {
            return BaseFragment.LoadResult.error;
        } else {
            return BaseFragment.LoadResult.success;
        }

    }

    public View createSucessView() {
       /* TextView textView =new TextView(this);
        textView.setText(appInfo.toString());
        return textView;*/
        View view = View.inflate(this, R.layout.activity_detail, null);
        //应用基本信息
        FrameLayout detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
        //应用安全信息
        FrameLayout detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        //截屏信息
        FrameLayout detail_screen = (FrameLayout) view.findViewById(R.id.detail_screen);
        //简介信息
        FrameLayout detail_des = (FrameLayout) view.findViewById(R.id.detail_des);
        //底部导航
        FrameLayout bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);

        DetailInfoHolder detailInfoHolder = new DetailInfoHolder();
        detail_info.addView(detailInfoHolder.getContenView());
        detailInfoHolder.setDatas(appInfo);

        DetailSafeHolder detailSafeHolder = new DetailSafeHolder();
        detail_safe.addView(detailSafeHolder.getContenView());
        detailSafeHolder.setDatas(appInfo);

        DetailScreenHolder detailScreenHolder = new DetailScreenHolder();
        detail_screen.addView(detailScreenHolder.getContenView());
        detailScreenHolder.setDatas(appInfo);

        DetailDesHolder detailDesHolder = new DetailDesHolder();
        detail_des.addView(detailDesHolder.getContenView());
        detailDesHolder.setDatas(appInfo);

        DetailBottomHolder detailBottomHolder = new DetailBottomHolder();
        bottom_layout.addView(detailBottomHolder.getContenView());
        return view;
    }


    @Override
    public void initData() {


    }


    @Override
    public void initActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
