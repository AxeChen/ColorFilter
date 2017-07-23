package com.mg.axe.colorfilter.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Axe on 2017/7/22.
 */

public class FilterItemView extends FilterView {

    private float[] colorFloat = {
            1f, 0f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f, 0f,
            0f, 0f, 1f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f,};

    private Bitmap bitmap;

    private Paint paint;

    private RectF rectF = new RectF();

    private ColorMatrixColorFilter colorMatrixColorFilter;
    private ColorMatrix colorMatrix;

    protected int scaleWidth = 0;
    protected int scaleHeight = 0;

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bitmap = bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scaleWidth = MeasureSpec.getSize(widthMeasureSpec);
        scaleHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectF.left = 0;
        rectF.top = 0;
        rectF.bottom = scaleHeight;
        rectF.right = scaleWidth;
    }

    public void setFloat(float[] floats) {
        colorMatrix = new ColorMatrix();
        colorMatrix.set(floats);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    public FilterItemView(Context context) {
        this(context, null);
    }

    public FilterItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //警用硬件加速
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.reset();
        paint.setAntiAlias(true);
        if (bitmap == null || colorMatrix == null) {
            return;
        }
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, null, rectF, paint);
        //
    }

}
