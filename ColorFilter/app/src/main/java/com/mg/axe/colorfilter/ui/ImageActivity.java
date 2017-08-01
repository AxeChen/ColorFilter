package com.mg.axe.colorfilter.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.FileUtils;
import com.mg.axe.colorfilter.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Axe on 2017/7/23.
 */

public class ImageActivity extends BaseActivity {

    public static final int REQUEST_ADJUST = 100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fImage)
    FilterImageView fImage;

    public String imageUrl;
    private ActionBar bar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        setFilterImage(fImage);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        initActionBar();
        setImageBitmap(2);
    }

    @OnClick({R.id.btnAdjust, R.id.btnMask, R.id.btnMatrx, R.id.btnSetMatrix})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdjust:
                Intent intentAdjust = new Intent(ImageActivity.this, AdjustActivity.class);
                intentAdjust.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intentAdjust, REQUEST_ADJUST);
                break;
            case R.id.btnMask:
                Intent intentMask = new Intent(ImageActivity.this, MaskActivity.class);
                intentMask.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intentMask, REQUEST_ADJUST);
                break;
            case R.id.btnMatrx:
                Intent intentMatrix = new Intent(ImageActivity.this, FiltersActivity.class);
                intentMatrix.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intentMatrix, REQUEST_ADJUST);
                break;
            case R.id.btnSetMatrix:
                Intent intentSet = new Intent(ImageActivity.this, ColorMatrixSetActivity.class);
                intentSet.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intentSet, REQUEST_ADJUST);
                break;
        }
    }

    private void setImageBitmap(int scale) {
        Bitmap bitmap = Utils.readBitmap(imageUrl, scale);
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.removeGroup(0);
        menu.add(1, MENU_SAVE, 0, "保存").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SAVE:
                if (filterImage == null) {
                    return false;
                }
                saveBitmapFile(filterImage.getChangeBitmap(), FileUtils.SAVEFILE);
                Intent intent = new Intent(ImageActivity.this, SaveActivity.class);
                startActivity(intent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String url = data.getStringExtra("imageUrl");
            if (url != null) {

                alreadyChange = true;
                imageUrl = url;
                //这里不需要再去压缩图片
                Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
                fImage.setImageBitmap(bitmap);
            }
        }
    }
}
