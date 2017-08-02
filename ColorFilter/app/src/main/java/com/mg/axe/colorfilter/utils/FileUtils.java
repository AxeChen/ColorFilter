package com.mg.axe.colorfilter.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by Axe on 2017/7/26.
 */

public class FileUtils {
    /**
     * 保存临时图片的临时图片，在保存渲染之后删除
     */
    public static final String TEMP_FILE = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "com.mg.axe.colorfilter" + File.separator + "temp" + File.separator + "temp.jpg";

    /**
     * 保存渲染后图片的路径(保存在相册里面)
     */
    public static final String SAVE_FILE = Environment
            .getExternalStorageDirectory().toString() + "/DCIM/Camera" + File.separator;

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录v
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.e("CacheUtils", "deleteFolderFile error" + e);
            }
        }
    }

}
