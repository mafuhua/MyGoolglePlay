package com.hellomc.mygoogle.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/27.
 */
public class ListBaseHolder extends BaseHolder<AppInfo> {
    ImageView item_icon;// 应用图标
    TextView item_title;// 应用名称
    RatingBar item_rating;// 评分
    TextView item_size;// 应用大小
    TextView item_desc;// 应用描述
    @Override
    public View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.app_item,null);
        this.item_icon = (ImageView) view.findViewById(R.id.item_icon);
        this.item_title = (TextView) view.findViewById(R.id.item_title);
        this.item_rating = (RatingBar) view.findViewById(R.id.item_rating);
        this.item_size = (TextView) view.findViewById(R.id.item_size);
        this.item_desc = (TextView) view.findViewById(R.id.item_bottom);
        return view;
    }

    @Override
    public void refreshView(AppInfo appInfo) {
        this.item_title.setText(appInfo.getName());// 应用名称
        // 评分
        this.item_rating.setRating((float) appInfo.getStars());
        // 应用大小(formatFileSize:xxxMB)
        String sizeMB = Formatter.formatFileSize(UIUtils.getContext(), appInfo.getSize());
        this.item_size.setText(sizeMB);
        // 应用描述
        this.item_desc.setText(appInfo.getDes());
        String imageUrl = HttpHelper.getImageUrl(appInfo.getIconUrl());// http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg
        // 应用图标
        BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(this.item_icon, imageUrl);
    }


}
