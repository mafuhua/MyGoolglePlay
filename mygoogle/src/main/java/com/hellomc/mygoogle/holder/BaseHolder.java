package com.hellomc.mygoogle.holder;

import android.view.View;

/**
 * Created by hellomc on 2015/12/27.
 */
public abstract class BaseHolder<T> {
    private View contentView;

    public View getContenView(){
        return contentView;
    }

    public BaseHolder() {
        contentView = initView();
        contentView.setTag(this);
    }

    public abstract View initView();
    public void setDatas(T data){
        refreshView(data);
    }
    public abstract void refreshView(T data);
}
