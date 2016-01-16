package com.hellomc.mygoogle.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.activity.MyApplication;

/**
 * Created by hellomc on 2015/12/25.
 */
public class UIUtils {
    public static String[] getStringArray() {
        return getContext().getResources().getStringArray(R.array.tab_names);
    }

    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //屏幕的密度dpi
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void removeParent(View view) {
        ViewParent viewParent = view.getParent();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(view);
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        long currentThread = Thread.currentThread().getId();
        if (currentThread == MyApplication.mainThread) {
            runnable.run();
        } else {
            MyApplication.mHandler.post(runnable);
        }
    }

    public static ScrollView getScrollView(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup instanceof ScrollView) {
                return (ScrollView) viewGroup;
            }else{
                return getScrollView(viewGroup);
            }
        }else {
            return null;
        }
    }
}
