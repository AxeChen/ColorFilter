package com.mg.axe.colorfilter.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by Axe on 2017/7/21.
 */

public class Utils {
    /**
     * 返回屏幕的宽高，用数组返回
     * 下标0，width。 下标1，height。
     *
     * @param context
     * @return
     */
    public static int[] getScreenWidth(Context context) {
        context = context.getApplicationContext();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        int[] size = new int[2];
        size[0] = width;
        size[1] = height;
        return size;
    }

    /**
     * 读取图片，按照缩放比保持长宽比例返回bitmap对象
     * <p>
     *
     * @param scale 缩放比例(1到10, 为2时，长和宽均缩放至原来的2分之1，为3时缩放至3分之1，以此类推)
     * @return Bitmap
     */
    public synchronized static Bitmap readBitmap(Context context, int res, int scale) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = scale;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeResource(context.getResources(), res, options);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 读取图片，按照缩放比保持长宽比例返回bitmap对象
     * <p>
     *
     * @param path  图片全路径
     * @param scale 缩放比例(1到10, 为2时，长和宽均缩放至原来的2分之1，为3时缩放至3分之1，以此类推)
     * @return Bitmap
     */
    public synchronized static Bitmap readBitmap(String path, int scale) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 设置缩放比例
            options.inSampleSize = scale;

            // 设置为false,解析Bitmap对象加入到内存中
            options.inJustDecodeBounds = false;

            // 设置内存不足时，比bitmap对象可以被回收
            options.inPurgeable = true;
            options.inInputShareable = true;

            options.inPreferredConfig = Bitmap.Config.RGB_565;

            Bitmap bitmap = BitmapFactory.decodeFile(path, options);

            // 判断一下图片是否旋转，如果旋转了，手动再转正
            // int degree = ImageManager.getExifOrientation(path);
            // if (degree != 0) {
            // bitmap = ImageManager.rotaingImageView(degree, bitmap);
            // }
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    //dp px
    public static int dp2px(Context context, int dpval) {
        context = context.getApplicationContext();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, context.getResources().getDisplayMetrics());
    }
}
