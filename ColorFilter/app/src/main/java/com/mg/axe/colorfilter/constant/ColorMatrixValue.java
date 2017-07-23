package com.mg.axe.colorfilter.constant;

/**
 * @Author Zaifeng
 * @Create 2017/7/21 0021
 * @Description 用于保存ColorMatrix特定的效果值
 */

public class ColorMatrixValue {

    /**
     * 原图
     */
    public static final float[] src = {
            1f, 0, 0, 0, 0,
            0, 1f, 0, 0, 0,
            0, 0, 1f, 0, 0,
            0, 0, 0, 1f, 0,
    };

    /**
     * 复古效果
     */
    public static final float[] vintage = {
            1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
            1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
            1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
            0, 0, 0, 1, 0,
    };

    /**
     * 灰度效果
     */
    public static final float[] gray = {
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0, 0, 0, 1, 0,
    };

    /**
     * 高亮效果
     */
    public static final float[] highLight = {
            1.3f, 0, 0, 0, 0,
            0, 1.3f, 0, 0, 0,
            0, 0, 1.3f, 0, 0,
            0, 0, 0, 1.3f, 0,
    };

    /**
     * 反色效果
     */
    public static final float[] removeColor = {
            -1f, 0, 0, 0, 255,
            0, -1f, 0, 0, 255,
            0, 0, -1f, 0, 255,
            0, 0, 0, 1f, 0,
    };

    /**
     * 绿色加强
     */
    public static final float[] green = {
            1f, 0, 0, 0, 0,
            0, 1.2f, 0, 0, 0,
            0, 0, 1f, 0, 0,
            0, 0, 0, 1f, 0,
    };

    /**
     * 去掉绿色
     */
    public static final float[] removeGreen = {
            1f, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 1f, 0, 0,
            0, 0, 0, 1f, 0,
    };


    /**
     * 红色加强
     */
    public static final float[] red = {
            1.2f, 0, 0, 0, 0,
            0, 1f, 0, 0, 0,
            0, 0, 1f, 0, 0,
            0, 0, 0, 1f, 0,
    };

    /**
     * 去掉红色
     */
    public static final float[] removeRed = {
            0, 0, 0, 0, 0,
            0, 1f, 0, 0, 0,
            0, 0, 1f, 0, 0,
            0, 0, 0, 1f, 0,
    };
    /**
     * 蓝色加强
     */
    public static final float[] blue = {
            1f, 0, 0, 0, 0,
            0, 1f, 0, 0, 0,
            0, 0, 1.2f, 0, 0,
            0, 0, 0, 1f, 0,
    };

    /**
     * 去掉蓝色
     */
    public static final float[] removeBlue = {
            1f, 0, 0, 0, 0,
            0, 1f, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 1f, 0,
    };


}
