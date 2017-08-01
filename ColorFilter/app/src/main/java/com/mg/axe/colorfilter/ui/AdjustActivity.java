package com.mg.axe.colorfilter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.Utils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author Zaifeng
 * @Create 2017/7/25 0025
 * @Description Content
 */

public class AdjustActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sbLight)
    SeekBar sbLight;
    @BindView(R.id.llLight)
    LinearLayout llLight;
    @BindView(R.id.sbSaturation)
    SeekBar sbSaturation;
    @BindView(R.id.rlAdjust)
    RelativeLayout rlAdjust;
    @BindView(R.id.adjustView)
    FilterImageView adjustView;

    private ActionBar bar;

    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        ButterKnife.bind(this);
        sbLight.setProgress(50);
        sbSaturation = (SeekBar) findViewById(R.id.sbSaturation);
        sbSaturation.setProgress(50);
        setImageBitmap();
        initListener();
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("调整属性");
        }
    }

    private void initListener() {

        sbLight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = progress / 50f;
                adjustView.changeLight(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbSaturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = progress / 50f;
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

    private void setImageBitmap() {
        Bitmap bitmap = null;
        if (alreadyChange) {
            bitmap = BitmapFactory.decodeFile(imageUrl);
        } else {
            bitmap = Utils.readBitmap(imageUrl, 2);
        }
        adjustView.setImageBitmap(bitmap);
        adjustView.setFloat(ColorMatrixValue.src);
    }

}
