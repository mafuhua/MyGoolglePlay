package com.hellomc.mygoogle.fragment;

import android.view.View;

import com.hellomc.mygoogle.adapter.ListBaseAdapter;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.protocol.GameProtocol;
import com.hellomc.mygoogle.utils.UIUtils;
import com.hellomc.mygoogle.view.BaseListView;

import java.util.List;

/**
 * Created by hellomc on 2015/12/25.
 */
public class GameFragment extends BaseFragment {

    private List<AppInfo> datas;
    private GameProtocol gameProtocol;

    @Override
    public View createSucessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas,listView){

            @Override
            public List<AppInfo> onLoadMore() {
                return gameProtocol.load(datas.size());
            }

        });
        return listView;

    }

    @Override
    public LoadResult load() {
        gameProtocol = new GameProtocol();
        datas = gameProtocol.load(0);
        return checkAppInfos(datas);
    }
}
