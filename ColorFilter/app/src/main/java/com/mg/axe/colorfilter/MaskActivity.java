package com.mg.axe.colorfilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.mask.MaskView;
import com.mg.axe.colorfilter.utils.Utils;

import java.io.File;

/**
 * @Author Zaifeng
 * @Create 2017/7/24 0024
 * @Description
 */

public class MaskActivity extends BaseActivity {

    private FilterImageView maskView;
    private SeekBar seekBar;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        setContentView(R.layout.activity_mask);
        maskView = (FilterImageView) findViewById(R.id.maskView);
        seekBar = (SeekBar) findViewById(R.id.sbMask);
        setImageBitmap();
        initListener();
    }

    public void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maskView.setMaskWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setImageBitmap() {
        Bitmap bitmap = Utils.readBitmap(imageUrl, 2);
        maskView.setImageBitmap(bitmap);
        maskView.setFloat(ColorMatrixValue.src);
    }


}
