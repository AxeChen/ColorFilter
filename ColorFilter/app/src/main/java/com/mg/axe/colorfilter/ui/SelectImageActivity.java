package com.mg.axe.colorfilter.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author Zaifeng
 * @Create 2017/7/26 0026
 * @Description 选择图片（拍照+自己选择照片）
 */

public class SelectImageActivity extends BaseActivity {

    private static final int REQUEST_CAMERA = 1;// 请求缩略图信号标识
    private static final int REQUEST_ALBUM = 2;// 请求原图信号标识

    @BindView(R.id.btnSelectFromXiangce)
    Button btnSelectFromXiangce;
    @BindView(R.id.btnTakePhoto)
    Button btnTakePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {

        btnSelectFromXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestAlbumPermiss()) {
                    selectPhoto();
                }
            }
        });

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestCameraPermiss()) {
                    takePhoto();

                }
            }
        });
    }

    private void takePhoto() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(FileUtils.TEMP_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = Uri.fromFile(file);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent2, REQUEST_CAMERA);
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ALBUM) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    intentImageActivity(imagePath);
                    c.close();
                }
            }

        } else if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                //拍照
                intentImageActivity(FileUtils.TEMP_FILE);
            }
        }
    }

    private void intentImageActivity(String url) {
        Intent intent = new Intent(SelectImageActivity.this, ImageActivity.class);
        intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, url);
        startActivity(intent);
    }
}
