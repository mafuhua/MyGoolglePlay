package com.hellomc.mygoogle.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by hellomc on 2015/12/25.
 */
public class CategoryFragment extends BaseFragment {


    @Override
    public View createSucessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("我是分类");
        return textView;
    }

    @Override
    public LoadResult load() {
        return LoadResult.error;
    }
}
