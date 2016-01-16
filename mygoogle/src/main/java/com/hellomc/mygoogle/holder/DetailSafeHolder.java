package com.hellomc.mygoogle.holder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;

import java.util.List;

/**
 * Created by hellomc on 2015/12/29.
 */
public class DetailSafeHolder extends BaseHolder<AppInfo> {
    //安全标题图片数组
    private ImageView[] imageTitles;
    //安全内容图片数组
    private ImageView[] imageContents;
    //安全内容信息数组
    private TextView[] textContents;
    private LinearLayout safe_content;
    private ImageView safe_arrow;
    private boolean isShow;

    @Override
    public View initView() {
        View safeView = View.inflate(UIUtils.getContext(), R.layout.detail_safe_holder, null);
        imageTitles = new ImageView[4];
        imageContents = new ImageView[4];
        textContents = new TextView[4];
        //安全标题
        imageTitles[0] = (ImageView) safeView.findViewById(R.id.iv_1);
        imageTitles[1] = (ImageView) safeView.findViewById(R.id.iv_2);
        imageTitles[2] = (ImageView) safeView.findViewById(R.id.iv_3);
        imageTitles[3] = (ImageView) safeView.findViewById(R.id.iv_4);
        //安全内容
        imageContents[0] = (ImageView) safeView.findViewById(R.id.des_iv_1);
        imageContents[1] = (ImageView) safeView.findViewById(R.id.des_iv_2);
        imageContents[2] = (ImageView) safeView.findViewById(R.id.des_iv_3);
        imageContents[3] = (ImageView) safeView.findViewById(R.id.des_iv_4);

        textContents[0] = (TextView) safeView.findViewById(R.id.des_tv_1);
        textContents[1] = (TextView) safeView.findViewById(R.id.des_tv_2);
        textContents[2] = (TextView) safeView.findViewById(R.id.des_tv_3);
        textContents[3] = (TextView) safeView.findViewById(R.id.des_tv_4);

        //箭头
        safe_arrow = (ImageView) safeView.findViewById(R.id.safe_arrow);
        //安全详情信息的根据
        safe_content = (LinearLayout) safeView.findViewById(R.id.safe_content);
        //点击箭头，控制safe_content显示有隐藏
        safe_content.setVisibility(View.GONE);//默认隐藏
        safe_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startValue = 0;
                int endValue = 0;
                if (!isShow) {
                    safe_content.setVisibility(View.VISIBLE);
                    isShow = true;
                    endValue = getSafeContentHeight();
                } else {
                  //  safe_content.setVisibility(View.GONE);
                    isShow = false;
                    startValue = getSafeContentHeight();
                }
                startAnimation(startValue, endValue);
            }
        });
        return safeView;
    }

    private void startAnimation(int startValue, int endValue) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(startValue, endValue);
        final ViewGroup.LayoutParams layoutParams = safe_content.getLayoutParams();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                layoutParams.height = value;
                safe_content.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (isShow) {
                    safe_arrow.setImageResource(R.drawable.arrow_up);
                } else {
                    safe_arrow.setImageResource(R.drawable.arrow_down);
                }
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    private int getSafeContentHeight() {
        safe_content.measure(0, 0);
        int measuredHeight = safe_content.getMeasuredHeight();
        return measuredHeight;
    }

    @Override
    public void refreshView(AppInfo data) {
        List<String> safeUrlList = data.getSafeUrlList();
        List<String> safeDesUrlList = data.getSafeDesUrlList();
        List<String> safeDesList = data.getSafeDesList();
        List<Integer> safeDesColorList = data.getSafeDesColorList();
        for (int i = 0; i < 4; i++) {
            if (i < safeUrlList.size()) {
                //显示内容
                imageTitles[i].setVisibility(View.VISIBLE);
                imageContents[i].setVisibility(View.VISIBLE);
                textContents[i].setVisibility(View.VISIBLE);
                //标题图片
                String imageTitleUrl = HttpHelper.getImageUrl(safeUrlList.get(i));
                BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(imageTitles[i], imageTitleUrl);
                //内容图片
                String imageContentUrl = HttpHelper.getImageUrl(safeDesUrlList.get(i));
                BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(imageContents[i], imageContentUrl);
                //内容信息
                textContents[i].setText(safeDesList.get(i));
                //
                int color;
                int colorType = safeDesColorList.get(i);
                if (colorType >= 1 && colorType <= 3) {//1,2,3
                    color = Color.rgb(255, 153, 0);
                } else if (colorType == 4) {//4
                    color = Color.rgb(0, 177, 62);
                } else {//0
                    color = Color.rgb(122, 122, 122);
                }
                textContents[i].setTextColor(color);

            } else {
                //隐藏
                imageTitles[i].setVisibility(View.GONE);
                imageContents[i].setVisibility(View.GONE);
                textContents[i].setVisibility(View.GONE);
            }
        }
    }


}
