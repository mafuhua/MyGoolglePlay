package com.hellomc.mygoogle.holder;


import android.view.View;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * 详情界面底部导航对应的holder
 * @author wangdh
 *
 */
public class DetailBottomHolder extends BaseHolder {
    
    @Override
    public View initView() {
        View bottomView = View.inflate(UIUtils.getContext(), R.layout.detail_bottom_holder, null);
        return bottomView;
    }
    
    @Override
    public void refreshView(Object data) {
        
    }
    
}