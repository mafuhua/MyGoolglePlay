package com.hellomc.mygoogle.holder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.AppInfo;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/28.
 */
public class DetailDesHolder extends BaseHolder<AppInfo> {
    private TextView des_content;//简介内容
    private TextView des_author;//作者
    private boolean isExpand = false;//是否展示
    private ImageView des_arrow;

    @Override
    public View initView() {
        View desView = View.inflate(UIUtils.getContext(), R.layout.detail_des_holder, null);
        des_content = (TextView) desView.findViewById(R.id.des_content);
        des_author = (TextView) desView.findViewById(R.id.des_author);
        //箭头
        des_arrow = (ImageView) desView.findViewById(R.id.des_arrow);
        ViewGroup.LayoutParams layoutParams = des_content.getLayoutParams();
        layoutParams.height = get7LinesHeight();
        des_content.setLayoutParams(layoutParams);
        des_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startHeight = 0;
                int endHeight = 0;

                if (!isExpand) {
                    isExpand = true;
                    startHeight = get7LinesHeight();
                    endHeight = getRealHeight();
                } else {
                    isExpand = false;
                    startHeight = getRealHeight();
                    endHeight = get7LinesHeight();
                }
                Log.d("DetailDesHolder", "startHeight:" + startHeight);
                Log.d("DetailDesHolder", "endHeight:" + endHeight);
                startAnimation(startHeight, endHeight);
            }
        });
        return desView;
    }

    private void startAnimation(int startHeight, int endHeight) {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) des_content.getLayoutParams();
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                //改变控件高度
                layoutParams.height = value;
                des_content.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束时，改变箭头
                if (isExpand) {
                    des_arrow.setImageResource(R.drawable.arrow_up);
                } else {
                    des_arrow.setImageResource(R.drawable.arrow_down);
                }
                Log.d("DetailDesHolder", "isExpand:" + isExpand);
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        final ScrollView scrollView = UIUtils.getScrollView(des_content);
        scrollView.postDelayed(new Runnable() {

            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 500);
    }

    /**
     * 获取真正的高度
     */
    public int getRealHeight() {
        //模式：未指定，精确值，最大值
        int widthMode = View.MeasureSpec.EXACTLY;
        int widthSize = des_content.getMeasuredWidth();//控件显示到屏幕上，宽度确定，可以直接获取
        //        des_content.measure(0, 0);//00未指定测量标准，只适用于控件本身没有显示
        //宽度尺子:mode+size
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(widthSize, widthMode);

        //高度尺子(0-size:1000)
//        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.AT_MOST);

        des_content.measure(widthMeasureSpec, 0);
        int measuredHeight = des_content.getMeasuredHeight();
        System.out.println("真正高度：" + measuredHeight);
        return measuredHeight;
    }

    /**
     * 7行高度
     *
     * @return
     */
    public int get7LinesHeight() {
//        des_content.setLines(7);
        TextView textView = new TextView(UIUtils.getContext());
        textView.setLines(7);//此方法会默认展示7行高度
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);//字体大小14dp
//        textView.setText(data.getDes());
        textView.measure(0, 0);
        int measuredHeight = textView.getMeasuredHeight();
        System.out.println("7行高度：" + measuredHeight);
        return measuredHeight;

    }

    @Override
    public void refreshView(AppInfo data) {
        des_content.setText(data.getDes());
        des_author.setText("作者:" + data.getAuthor());
    }
}
