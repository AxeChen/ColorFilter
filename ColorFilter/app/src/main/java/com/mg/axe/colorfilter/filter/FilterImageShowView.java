package com.mg.axe.colorfilter.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mg.axe.colorfilter.utils.Utils;

/**
 * Created by Axe on 2017/7/23.
 */

public class FilterImageShowView extends FilterView {

    private Bitmap bitmap;
    private int screenWidth = 0;
    private int screenHeight = 0;
    protected int scaleWidth = 0;
    protected int scaleHeight = 0;

    public FilterImageShowView(Context context) {
        this(context, null);
    }

    public FilterImageShowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterImageShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = Utils.getScreenWidth(context)[0];
        screenHeight = Utils.getScreenWidth(context)[1];
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.bitmap = bm;
        scaleBitmap();
        setImageSize();
    }

    public void setImageSize() {
        if (getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = scaleWidth;
            layoutParams.height = scaleHeight;
            this.setLayoutParams(layoutParams);

        } else if (getParent() instanceof LinearLayout) {

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = scaleWidth;
            layoutParams.height = scaleHeight;
            this.setLayoutParams(layoutParams);

        } else if (getParent() instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = scaleWidth;
            layoutParams.height = scaleHeight;
            this.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void scaleBitmap() {
        super.scaleBitmap();
        if (bitmap == null) {
            return;
        }
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        float scale = 0;
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
            int maxHeight = screenHeight*5/6;
            if (bHeight > maxHeight) {
                scale = bHeight * 1f / maxHeight * 1f;
                scaleWidth = (int) (bWidth * 1f / scale);
            } else {
                scale = maxHeight * 1f / bHeight;
                scaleWidth = (int) (bWidth * 1f * scale);
            }
            scaleHeight = screenHeight;
        } else {
            scaleWidth = screenWidth;
            scaleHeight = screenWidth;
        }
    }
}
