package com.mg.axe.colorfilter.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Axe on 2017/8/1.
 */

public class SaveActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvSaveDri)
    TextView tvSaveDri;
    @BindView(R.id.ivImage)
    FilterImageView ivImage;

    private ActionBar bar;
    private Bitmap bitmap;

    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        bitmap = BitmapFactory.decodeFile(imageUrl);
        ButterKnife.bind(this);
        initActionBar();
        setBitmap();
        setSaveAddress();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("美化完成");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.removeGroup(0);
        menu.removeGroup(1);
        menu.add(1, MENU_SHARE, 0, "分享图片").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SHARE:
                if (bitmap == null) {
                    return false;
                }
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM, imageUrl);
                sendIntent.setType("image/");
                startActivity(Intent.createChooser(sendIntent, "分享图片"));
                //调用分享的代码
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setSaveAddress() {
        tvSaveDri.setText("图片保存的地址：\n" + FileUtils.SAVE_FILE);
    }

    private void setBitmap() {
        if (bitmap != null) {
            ivImage.setImageBitmap(bitmap);
            ivImage.setFloat(ColorMatrixValue.src);
        }
    }
}
