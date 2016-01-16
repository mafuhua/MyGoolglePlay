package com.hellomc.mygoogle.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by hellomc on 2015/12/26.
 */
public class FileUtils {
    public static File getFileCacheRoot(String fileType) {
        StringBuffer sb = new StringBuffer();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());//  mnt/sdcard
        sb.append(File.separator);// mnt/sdcard/
        sb.append("GooglePlay09");// mnt/sdcard/GooglePlay09
        sb.append(File.separator);// mnt/sdcard/GooglePlay09/
        sb.append(fileType);// mnt/sdcard/GooglePlay09/cache
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getFileCacheJson() {
        return getFileCacheRoot("cache");
    }

    public static File getFileCacheIcon() {
        File file = getFileCacheRoot("icon");
        return file;
    }
}
