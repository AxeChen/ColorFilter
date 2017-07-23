package com.mg.axe.colorfilter.filter;

import android.graphics.Bitmap;

/**
 * Created by Axe on 2017/7/23.
 */

public interface Filter {

    /**
     * 设置滤镜的
     *
     * @param floats
     */
    public void setFloat(float[] floats);

    /**
     * 获取处理过的bitmap
     *
     * @return
     */
    public Bitmap getChangeBitmap();

    /**
     * 缩放bitmap
     *
     */
    public void scaleBitmap();

}
