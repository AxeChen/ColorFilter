package com.mg.axe.colorfilter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author Zaifeng
 * @Create 2017/7/24 0024
 * @Description
 */

public class MaskActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sbMask)
    SeekBar sbMask;
    @BindView(R.id.llAdjust)
    LinearLayout llAdjust;
    @BindView(R.id.maskView)
    FilterImageView maskView;
    private String imageUrl;

    private ActionBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        setContentView(R.layout.activity_mask);
        ButterKnife.bind(this);
        initActionBar();
        setImageBitmap();
        initListener();
    }

    public void initListener() {
        sbMask.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("虚化边框");
        }
    }

    private void setImageBitmap() {
        Bitmap bitmap = Utils.readBitmap(imageUrl, 2);
        maskView.setImageBitmap(bitmap);
        maskView.setFloat(ColorMatrixValue.src);
    }


}
