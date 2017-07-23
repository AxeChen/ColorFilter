package com.mg.axe.colorfilter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Axe on 2017/7/23.
 */

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btn;

    String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "com.mg.axe.colorfilters" + File.separator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、判断是否有打电话的权限
        checkPermisson();
        setContentView(R.layout.activity_image);
        imageView = (ImageView) findViewById(R.id.fImage);
        btn = (Button) findViewById(R.id.btnMatrx);

//        imageView.setImageBitmap(bitmap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, MainActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
    }

    public final static int PERMISSION_FILE = 1;

    public void checkPermisson() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有获取权限则做权限处理
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_FILE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_FILE:
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    call();
//                }else{
//                    //用户没有授予权限，
//                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000) {
//            if (resultCode == Activity.RESULT_OK) {
//                Bitmap bitmap = data.getParcelableExtra("bitmap");
//                imageView.setImageBitmap(bitmap);
//            }
//        }
        Bitmap bitmap = BitmapFactory.decodeFile(path + "cachetest.jpg");
        imageView.setImageBitmap(bitmap);
    }
}
