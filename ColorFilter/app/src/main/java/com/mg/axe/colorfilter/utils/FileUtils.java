package com.mg.axe.colorfilter.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Axe on 2017/7/26.
 */

public class FileUtils {
    /**
     * 保存临时图片的临时图片，在保存渲染之后删除
     */
    public static final String TEMPFILE = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "com.mg.axe.colorfilter" + File.separator + "temp" + File.separator + "temp.jpg";

    /**
     * 保存渲染后图片的路径
     */
    public static final String SAVEFILE = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "com.mg.axe.colorfilter" + File.separator + "save" + File.separator;

}
