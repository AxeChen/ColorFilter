package com.mg.axe.colorfilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import java.io.File;

/**
 * @Author Zaifeng
 * @Create 2017/7/25 0025
 * @Description Content
 */

public class AdjustActivity extends AppCompatActivity {

    String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "com.mg.axe.colorfilters" + File.separator;
    private AdjustView adjustView;
    private SeekBar lightSeekBar;
    private SeekBar saturationSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);
        adjustView = (AdjustView) findViewById(R.id.adjustView);
        lightSeekBar = (SeekBar) findViewById(R.id.sbLight);
        lightSeekBar.setProgress(50);
        saturationSeekBar = (SeekBar) findViewById(R.id.sbSaturation);
        saturationSeekBar.setProgress(50);
        setImage();
        initListener();
    }

    private void initListener() {

        lightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = progress/50f;
                adjustView.changeLight(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = progress/50f;
                adjustView.changeSaturation(value);
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
        adjustView.setImageBitmap(bitmap);
    }
}
