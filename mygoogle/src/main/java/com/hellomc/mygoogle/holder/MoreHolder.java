package com.hellomc.mygoogle.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.adapter.DefaultAdapter;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/27.
 */
public class MoreHolder extends BaseHolder<Integer> {
    public static final int load_error = 0;//加载失败
    public static final int has_more = 1;//请求到数据
    public static final int has_no_more = 2;//没有请求到数据
    //加载中
    private RelativeLayout rl_more_loading;
    //加载失败
    private RelativeLayout rl_more_error;
    private DefaultAdapter adapter;

    public  MoreHolder(DefaultAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.more_holder, null);
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        //如果是加载失败，应该显示加载失败界面
        rl_more_error.setVisibility(data == load_error?View.VISIBLE:View.GONE);
        //如果是加载到数据，应该显示加载中
        rl_more_loading.setVisibility(data == has_more ? View.VISIBLE:View.GONE);
    }



    @Override
    public View getContenView() {
        adapter.loadMore();
        return super.getContenView();
    }
}
