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
 * 详情界面基本信息对应的holder
 * @author wangdh
 *
 */
public class DetailInfoHolder extends BaseHolder<AppInfo> {
    
    private ImageView item_icon;//应用图标
    private TextView item_title;//应用名称
    private RatingBar item_rating;//评分
    private TextView item_download;//下载量
    private TextView item_version;//版本
    private TextView item_date;//日期
    private TextView item_size;//大小

    @Override
    public View initView() {
        View infoView = View.inflate(UIUtils.getContext(), R.layout.detail_info_holder, null);
        item_icon = (ImageView) infoView.findViewById(R.id.item_icon);
        item_title = (TextView) infoView.findViewById(R.id.item_title);
        item_rating = (RatingBar) infoView.findViewById(R.id.item_rating);
        item_download = (TextView) infoView.findViewById(R.id.item_download);
        item_version = (TextView) infoView.findViewById(R.id.item_version);
        item_date = (TextView) infoView.findViewById(R.id.item_date);
        item_size = (TextView) infoView.findViewById(R.id.item_size);
        
        return infoView;
    }
    
    @Override
    public void refreshView(AppInfo data) {
        //应用图标
        String iconUrl = HttpHelper.getImageUrl(data.getIconUrl());
        BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(item_icon, iconUrl);
        //应用名称
        item_title.setText(data.getName());
        //评分
        item_rating.setRating((float) data.getStars());
        //下载量
        item_download.setText("下载量："+data.getDownloadNum());
        //版本
        item_version.setText("版本："+data.getVersion());
        //发布日期
        item_date.setText("时间："+data.getDate());
        //应用大小
        String size = Formatter.formatFileSize(UIUtils.getContext(), data.getSize());
        item_size.setText("大小："+size);
    }
    
}