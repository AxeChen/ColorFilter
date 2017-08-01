package com.mg.axe.colorfilter.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.FileUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Axe on 2017/7/29.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String TAG_STRING_IMAGE_URL = "tagStringImageUrl";

    public static final int MENU_SAVE = 2;
    public static final int MENU_COMPLETE = 3;

    private static int PERMISSION_CAMERA = 100;

    //是否已经改变了图片,如果已经改变了图片就无需再压缩展示
    protected boolean alreadyChange = false;

    protected FilterImageView filterImage;

    public void setFilterImage(FilterImageView filterImage) {
        this.filterImage = filterImage;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.removeGroup(0);
        menu.add(0, MENU_COMPLETE, 0, "确定").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case MENU_COMPLETE:
                if (filterImage == null) {
                    return false;
                }
                saveBitmapFile(filterImage.getChangeBitmap());
                Intent intent = new Intent();
                intent.putExtra("imageUrl", FileUtils.TEMPFILE);
                setResult(RESULT_OK, intent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveBitmapFile(Bitmap bitmap) {
        saveBitmapFile(bitmap,FileUtils.TEMPFILE);
    }

    public void saveBitmapFile(Bitmap bitmap,String url) {
        try {
            File file = new File(url);//将要保存图片的路径
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //申请相机权限
    protected boolean requestCameraPermiss() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
            return false;
        }
        return true;
    }

    //申请读取文件权限
    protected boolean requestAlbumPermiss() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CAMERA);
            return false;
        }
        return true;

    }
}
