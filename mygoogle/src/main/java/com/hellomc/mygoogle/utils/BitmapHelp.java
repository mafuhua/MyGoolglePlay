package com.hellomc.mygoogle.utils;


import android.content.Context;

import com.hellomc.mygoogle.R;
import com.lidroid.xutils.BitmapUtils;

/**
 * BitmapUtils的单例
 * @author wangdh
 *
 */
public class BitmapHelp {
    private BitmapHelp() {
    }

    private static BitmapUtils bitmapUtils;

    /**
     * BitmapUtils不是单例的 根据需要重载多个获取实例的方法
     *
     * @param appContext application context
     * @return
     */
    public static BitmapUtils getBitmapUtils(Context appContext) {
        if (bitmapUtils == null) {
            /**
             * diskCachePath:本地缓存路径
             * memoryCachePercent:内存缓存百分比，0.08 - 0.8
             * diskCacheSize:本地缓存大小，单位未知
             */
            bitmapUtils = new BitmapUtils(appContext, FileUtils.getFileCacheIcon().getAbsolutePath(), 0.3f);
            //当前加载中显示的图片
            bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);
            //加载失败图片
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
        }
        return bitmapUtils;
    }
}
