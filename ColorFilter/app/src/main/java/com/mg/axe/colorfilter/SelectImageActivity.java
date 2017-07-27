package com.mg.axe.colorfilter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

public class SelectImageActivity extends AppCompatActivity {

    private static int REQUEST_CAMERA = 1;// 请求缩略图信号标识
    private static int REQUEST_ALBUM = 2;// 请求原图信号标识

    private static int PERMISSION_CAMERA = 100;

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

    //申请权限
    private void requestCameraPermiss() {
        //1、判断是否有打电话的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //没有获取权限则做权限处理
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
        } else {
            takePhoto();
        }
    }

    private void requestAlbumPermiss() {
        //1、判断是否有打电话的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有获取权限则做权限处理
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CAMERA);

        } else {
            selectPhoto();
        }

    }


    private void initListener() {

        btnSelectFromXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAlbumPermiss();

            }
        });

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermiss();
            }
        });
    }

    private void takePhoto() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(FileUtils.TEMPFILE);
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
            //从相册中选择图片
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
        } else if (requestCode == REQUEST_CAMERA) {
            //拍照
            intentImageActivity(FileUtils.TEMPFILE);
        }
    }

    private void intentImageActivity(String url) {
        Intent intent = new Intent(SelectImageActivity.this, ImageActivity.class);
        intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, url);
        startActivity(intent);
    }
}
