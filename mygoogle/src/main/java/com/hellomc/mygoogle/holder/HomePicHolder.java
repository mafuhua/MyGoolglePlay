package com.hellomc.mygoogle.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.hellomc.mygoogle.activity.MyApplication;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;

import java.util.List;

/**
 * Created by hellomc on 2015/12/28.
 */
public class HomePicHolder extends BaseHolder<List<String>> {
    private ViewPager viewPager;
    private List<String> data;

    @Override
    public View initView() {
        viewPager = new ViewPager(UIUtils.getContext());
        int dip2px = UIUtils.dip2px(UIUtils.getContext(), 134);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, dip2px);
        viewPager.setLayoutParams(layoutParams);
        return viewPager;
    }

    @Override
    public void refreshView(List<String> data) {
        this.data = data;
        viewPager.setAdapter(new PicAdapter());
        viewPager.setCurrentItem(1000 * data.size());
        final AutoTask autoTask = new AutoTask();
        autoTask.start();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        autoTask.stop();
                        break;
                    case MotionEvent.ACTION_UP:
                        autoTask.start();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        autoTask.start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    class AutoTask implements Runnable {

        private boolean isStart = false;

        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            MyApplication.mHandler.postDelayed(this, 2000);
        }

        public void start() {
            if (!isStart) {
                isStart = true;
                MyApplication.mHandler.postDelayed(this, 2000);
            }
            MyApplication.mHandler.postDelayed(this, 2000);
        }

        public void stop() {
            if (isStart) {
                isStart = false;
                MyApplication.mHandler.removeCallbacks(this);
            }
        }
    }

    class PicAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % data.size();
            ImageView imageView = new ImageView(UIUtils.getContext());
            String imageUrl = data.get(position);
            imageUrl = HttpHelper.getImageUrl(imageUrl);
            container.addView(imageView);
            BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(imageView, imageUrl);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
