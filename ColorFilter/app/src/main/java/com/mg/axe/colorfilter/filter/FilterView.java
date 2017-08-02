package com.mg.axe.colorfilter.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Axe on 2017/7/23.
 */

public abstract class FilterView extends android.support.v7.widget.AppCompatImageView implements Filter {

    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    public void setFloat(float[] floats) {

    }

    @Override
    public Bitmap getChangeBitmap() {
        return null;
    }


    @Override
    public void scaleBitmap() {
    }
}
