package com.mg.axe.colorfilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.mg.axe.colorfilter.mask.MaskView;

import java.io.File;

/**
 * @Author Zaifeng
 * @Create 2017/7/24 0024
 * @Description Content
 */

public class MaskActivity extends AppCompatActivity {

    private MaskView maskView;
    private SeekBar seekBar;

    String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "com.mg.axe.colorfilters" + File.separator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask);
        maskView = (MaskView) findViewById(R.id.maskView);
        seekBar = (SeekBar) findViewById(R.id.sbMask);
        setImage();
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

    public void setImage() {
        Bitmap bitmap = BitmapFactory.decodeFile(path + "cachetest.jpg");
        maskView.setImageBitmap(bitmap);
    }


}
