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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageShowView;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.Utils;

import java.io.File;

/**
 * Created by Axe on 2017/7/23.
 */

public class ImageActivity extends AppCompatActivity {

    public static final String TAG_STRING_IMAGE_URL = "tagStringImageUrl";

    private FilterImageView imageView;
    private Button btn;
    private Button maskBtn;
    private Button adjustBtn;
    private Button setMatrix;

    public String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);

        imageView = (FilterImageView) findViewById(R.id.fImage);
        btn = (Button) findViewById(R.id.btnMatrx);
        setMatrix = (Button) findViewById(R.id.btnSetMatrix);
        adjustBtn = (Button) findViewById(R.id.btnAdjust);
        maskBtn = (Button) findViewById(R.id.btnMask);
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        setImageBitmap();
        setMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, ColorMatrixSetActivity.class);
                intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intent);
            }
        });

        adjustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, AdjustActivity.class);
                intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intent);
            }
        });
        maskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, MaskActivity.class);
                intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, MainActivity.class);
                intent.putExtra(ImageActivity.TAG_STRING_IMAGE_URL, imageUrl);
                startActivityForResult(intent, 1000);
            }
        });
    }

    private void setImageBitmap() {
        Bitmap bitmap = Utils.readBitmap(imageUrl, 2);
        imageView.setImageBitmap(bitmap);
        imageView.setFloat(ColorMatrixValue.src);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
//        imageView.setImageBitmap(bitmap);
    }
}
