package com.hellomc.mygoogle.adapter;

import android.widget.ListView;

import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.holder.BaseHolder;
import com.hellomc.mygoogle.holder.ListBaseHolder;

import java.util.List;

/**
 * Created by hellomc on 2015/12/27.
 */
public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List<AppInfo> datas,ListView listView) {
        super(datas,listView);
    }



    @Override
    public BaseHolder getHolder() {
        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onLoadMore();
}
