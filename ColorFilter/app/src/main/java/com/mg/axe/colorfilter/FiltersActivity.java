package com.mg.axe.colorfilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterBean;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.filter.FilterItemView;
import com.mg.axe.colorfilter.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiltersActivity extends BaseActivity {

    public List<FilterBean> beenList = new ArrayList<>();
    public Bitmap bitmap;
    public Bitmap itemBitmap;
    public Button okBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fImage)
    FilterImageView fImage;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.matrixSelect)
    RecyclerView matrixSelect;

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initActionBar();

        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        fImage = (FilterImageView) findViewById(R.id.fImage);
        matrixSelect = (RecyclerView) findViewById(R.id.matrixSelect);
        okBtn = (Button) findViewById(R.id.btnOk);
        itemBitmap = Utils.readBitmap(this, R.mipmap.damimi, 4);
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
        getFilterDatas();
        matrixSelect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        matrixSelect.setAdapter(new ColorFilterAdapter());

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmapFile(fImage.getChangeBitmap());
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        setImageBitmap();
        setItemBitmap();
    }

    private void initActionBar() {
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("添加滤镜");
        }
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

    private String imageUrl;

    private void setImageBitmap() {
        Bitmap bitmap = Utils.readBitmap(imageUrl, 2);
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
    }

    private void setItemBitmap() {
        itemBitmap = Utils.readBitmap(imageUrl, 5);
    }

    public void saveBitmapFile(Bitmap bitmap) {
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "com.mg.axe.colorfilters" + File.separator;

        try {
            File file = new File(path + "cachetest.jpg");//将要保存图片的路径
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

    public void getFilterDatas() {
        beenList.add(new FilterBean("原图", ColorMatrixValue.src));
        beenList.add(new FilterBean("灰度", ColorMatrixValue.gray));
        beenList.add(new FilterBean("复古", ColorMatrixValue.vintage));
        beenList.add(new FilterBean("高亮", ColorMatrixValue.highLight));
        beenList.add(new FilterBean("反相", ColorMatrixValue.removeColor));
        beenList.add(new FilterBean("红色加强", ColorMatrixValue.red));
        beenList.add(new FilterBean("去掉红色", ColorMatrixValue.removeRed));
        beenList.add(new FilterBean("绿色加强", ColorMatrixValue.green));
        beenList.add(new FilterBean("去掉绿色", ColorMatrixValue.removeGreen));
        beenList.add(new FilterBean("蓝色加强", ColorMatrixValue.blue));
        beenList.add(new FilterBean("去掉蓝色", ColorMatrixValue.removeBlue));
    }

    public class ColorFilterAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(FiltersActivity.this).inflate(R.layout.item_color_filter, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            bindItemView((ItemViewHolder) holder, position);
        }

        @Override
        public int getItemCount() {
            return beenList.size();
        }

        public void bindItemView(ItemViewHolder itemViewHolder, int position) {
            FilterBean filterBean = beenList.get(position);
            itemViewHolder.itemImg.setImageBitmap(itemBitmap);
            itemViewHolder.itemImg.setFloat(filterBean.getFilterFloats());
            itemViewHolder.itemText.setText(filterBean.getName());
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {

            private FilterItemView itemImg;
            private TextView itemText;
            private RelativeLayout frameLayout;

            public ItemViewHolder(View itemView) {
                super(itemView);
                itemImg = (FilterItemView) itemView.findViewById(R.id.itemImg);
                frameLayout = (RelativeLayout) itemView.findViewById(R.id.itemContent);
                itemText = (TextView) itemView.findViewById(R.id.itemName);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = getAdapterPosition();
                        if (index >= 0) {
                            FilterBean bean = beenList.get(index);
                            fImage.setFloat(bean.getFilterFloats());
                        }
                    }
                });

            }
        }

    }


}
