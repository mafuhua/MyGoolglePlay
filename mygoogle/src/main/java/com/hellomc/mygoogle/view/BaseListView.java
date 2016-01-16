package com.hellomc.mygoogle.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/26.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context) {
        super(context);
        initSetting();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();

    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();


    }

    private void initSetting() {
        setSelector(android.R.color.transparent);
        setCacheColorHint(getResources().getColor(android.R.color.transparent));
        setDivider(UIUtils.getContext().getResources().getDrawable(R.drawable.nothing));
    }


}
