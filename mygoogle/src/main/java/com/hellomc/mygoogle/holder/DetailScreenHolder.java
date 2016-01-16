package com.hellomc.mygoogle.holder;

import android.view.View;
import android.widget.ImageView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;

import java.util.List;

/**
 * Created by hellomc on 2015/12/28.
 */
public class DetailScreenHolder extends BaseHolder<AppInfo>{
    private ImageView [] imageViews;

    @Override
    public View initView() {
        View screenView = View.inflate(UIUtils.getContext(), R.layout.detail_screen_holder, null);
        imageViews = new ImageView[5];
        imageViews[0] = (ImageView) screenView.findViewById(R.id.screen_1);
        imageViews[1] = (ImageView) screenView.findViewById(R.id.screen_2);
        imageViews[2] = (ImageView) screenView.findViewById(R.id.screen_3);
        imageViews[3] = (ImageView) screenView.findViewById(R.id.screen_4);
        imageViews[4] = (ImageView) screenView.findViewById(R.id.screen_5);
        return screenView;
    }

    @Override
    public void refreshView(AppInfo data) {
        List<String> screenList = data.getScreenList();//4
        for (int i = 0; i < 5; i++) {
            if(i<screenList.size()){//显示
                imageViews[i].setVisibility(View.VISIBLE);
                String imageUrl = HttpHelper.getImageUrl(screenList.get(i));
                BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(imageViews[i], imageUrl);
            }else{//隐藏
                imageViews[i].setVisibility(View.GONE);
            }
        }

    }

}
