package com.mg.axe.colorfilter.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @Author Zaifeng
 * @Create 2017/7/21 0021
 * @Description Content
 */

public class FilterSelectView extends View  {

    private LinearLayout mLLSelectTitle;

    private int screenWidth = 0;
    private int screenHeight = 0;
    private float scale = 0;
    protected int scaleWidth = 0;
    protected int scaleHeight = 0;


    public FilterSelectView(Context context) {
        this(context, null);
    }

    public FilterSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void scaleBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        if (bWidth > bHeight) {
            //最大宽为屏幕的宽度
            if (bWidth > screenWidth) {
                scale = bWidth * 1f / screenWidth * 1f;
                scaleHeight = (int) (bHeight * 1f / scale);
            } else {
                scale = screenWidth * 1f / bWidth * 1f;
                scaleHeight = (int) (bHeight * 1f * scale);
            }
            scaleWidth = screenWidth;
        } else if (bWidth < bHeight) {
            //最大高为屏幕的 5/6
            if (bHeight > screenHeight) {
                scale = bHeight * 1f / scaleHeight * 1f;
                scaleWidth = (int) (bWidth * 1f / scale);
            } else {
                scale = scaleHeight * 1f / bHeight;
                scaleWidth = (int) (bWidth * 1f * scale);
            }
            scaleHeight = screenHeight;
            /*if (bHeight > screenHeight * 0.5f && bHeight < screenHeight) {
                scale = scaleHeight * 1f / bHeight;
            } else */
        } else {
            scaleWidth = screenWidth;
            scaleHeight = screenWidth;
        }
    }
}
