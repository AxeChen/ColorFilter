package com.mg.axe.colorfilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Axe on 2017/7/23.
 */

public class ImageActivity extends AppCompatActivity {

    public static final String TAG_STRING_IMAGE_URL = "tagStringImageUrl";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnMatrx)
    Button btnMatrx;
    @BindView(R.id.btnMask)
    Button btnMask;
    @BindView(R.id.btnAdjust)
    Button btnAdjust;
    @BindView(R.id.btnSetMatrix)
    Button btnSetMatrix;
    @BindView(R.id.bottomItem)
    LinearLayout bottomItem;
    @BindView(R.id.fImage)
    FilterImageView fImage;

    public String imageUrl;
    private ActionBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        initActionBar();
        setImageBitmap();
    }


    @OnClick({R.id.btnAdjust, R.id.btnMask, R.id.btnMatrx, R.id.btnSetMatrix})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdjust:
                Intent intentAdjust = new Intent(ImageActivity.this, AdjustActivity.class);
                intentAdjust.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intentAdjust);
                break;
            case R.id.btnMask:
                Intent intentMask = new Intent(ImageActivity.this, MaskActivity.class);
                intentMask.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intentMask);
                break;
            case R.id.btnMatrx:
                Intent intentMatrx = new Intent(ImageActivity.this, FiltersActivity.class);
                intentMatrx.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intentMatrx, 1000);
                break;
            case R.id.btnSetMatrix:
                Intent intentSet = new Intent(ImageActivity.this, ColorMatrixSetActivity.class);
                intentSet.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intentSet);
                break;
        }
    }

    private void setImageBitmap() {
        Bitmap bitmap = Utils.readBitmap(imageUrl, 2);
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("图片修改");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
        fImage.setImageBitmap(bitmap);
    }
}
