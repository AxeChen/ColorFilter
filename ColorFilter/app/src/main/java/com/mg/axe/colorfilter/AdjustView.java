package com.mg.axe.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mg.axe.colorfilter.filter.FilterView;
import com.mg.axe.colorfilter.utils.Utils;

/**
 * @Author Zaifeng
 * @Create 2017/7/24 0024
 * @Description 调节对比度，单个颜色
 */

public class AdjustView extends FilterView {

    private Bitmap bitmap;
    private Paint paint;
    private RectF rectF;

    private int screenWidth = 0;
    private int screenHeight = 0;
    protected int scaleWidth = 0;
    protected int scaleHeight = 0;

    private BlurMaskFilter blurMaskFilter;

    public AdjustView(Context context) {
        this(context, null);
    }

    public AdjustView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdjustView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //警用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        screenWidth = Utils.getScreenWidth(context)[0];
        screenHeight = Utils.getScreenWidth(context)[1];
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setMaskWidth(int maskWidth) {
        if (maskWidth == 0) {
            blurMaskFilter = null;
        } else {
            blurMaskFilter = new BlurMaskFilter(maskWidth * 2, BlurMaskFilter.Blur.NORMAL);
        }
        invalidate();
    }

    float r = 1f;
    float g = 1f;
    float b = 1f;
    float a = 1f;

    float[] floats = {
            r, 0, 0, 0, 0,
            0, g, 0, 0, 0,
            0, 0, b, 0, 0,
            0, 0, 0, a, 0,
    };

    private ColorMatrix colorMatrix;
    private ColorMatrixColorFilter colorMatrixColorFilter;

    /**
     * @param light 0f -2f
     */
    public void changeLight(float light) {
        colorMatrix = new ColorMatrix();
        colorMatrix.setScale(light, light, light, light);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    /**
     * @param saturaction 0f - 2f
     */
    public void changeSaturation(float saturaction) {
        colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturaction);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (bitmap != null) {
            setMeasuredDimension(scaleWidth, scaleHeight);
            scaleBitmap();
            invalidate();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bitmap = bitmap;
        scaleBitmap();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        paint.reset();
        paint.setAntiAlias(true);
        if (bitmap == null || colorMatrix == null) {
            return;
        }
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, null, rectF, paint);
        canvas.save();
    }

    @Override
    public Bitmap getChangeBitmap() {
        Bitmap bitmapAltered = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapAltered);//bitmap提供了画布，只在此提供了大小尺寸，偏移后并未有背景显示出来 
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, 0, 0, paint);//绘制的图片和之前的一模一样 
        return bitmapAltered;
    }

    @Override
    public void scaleBitmap() {
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
            if (bHeight > screenHeight) {
                scale = bHeight * 1f / scaleHeight * 1f;
                scaleWidth = (int) (bWidth * 1f / scale);
            } else {
                scale = scaleHeight * 1f / bHeight;
                scaleWidth = (int) (bWidth * 1f * scale);
            }
            scaleHeight = screenHeight;
        } else {
            scaleWidth = screenWidth;
            scaleHeight = screenWidth;
        }
        rectF = new RectF(0, 0, scaleWidth, scaleHeight);
    }
}
