package com.mg.axe.colorfilter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg.axe.colorfilter.R;
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
    ImageView ivImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);
        setBitmap();
    }

    private void setBitmap(){
        Bitmap bitmap = BitmapFactory.decodeFile(FileUtils.SAVEFILE);
        
    }
}
