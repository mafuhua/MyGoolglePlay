package com.hellomc.mygoogle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hellomc.mygoogle.activity.DetailActivity;
import com.hellomc.mygoogle.adapter.ListBaseAdapter;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.holder.HomePicHolder;
import com.hellomc.mygoogle.protocol.HomeProtocol;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;
import com.hellomc.mygoogle.view.BaseListView;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

/**
 * Created by hellomc on 2015/12/25.
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfo> appInfos;
    private HomeProtocol homeProtocol;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadAndView();
    }


    public View createSucessView() {
        final BaseListView listView = new BaseListView(UIUtils.getContext());
        HomePicHolder homePicHolder = new HomePicHolder();
        listView.addHeaderView(homePicHolder.getContenView());
        homePicHolder.setDatas(homeProtocol.getImageUrls());
        listView.setAdapter(new ListBaseAdapter(appInfos,listView){
            @Override
            protected List<AppInfo> onLoadMore() {
                return homeProtocol.load(appInfos.size());
            }

            @Override
            public void OnInnerItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                AppInfo appInfo = appInfos.get(position-listView.getHeaderViewsCount());
                intent.putExtra("packageName",appInfo.getPackageName());
                getActivity().startActivity(intent);

            }
        });

        listView.setOnScrollListener(new PauseOnScrollListener(BitmapHelp.getBitmapUtils(getActivity()), false, true));
        return listView;
    }

    @Override
    public LoadResult load() {
        homeProtocol = new HomeProtocol();
        appInfos = homeProtocol.load(0);
        return checkAppInfos(appInfos);
    }


}
