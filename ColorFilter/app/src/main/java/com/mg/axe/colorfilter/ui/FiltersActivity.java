package com.mg.axe.colorfilter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.bean.FilterBean;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.filter.FilterItemView;
import com.mg.axe.colorfilter.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiltersActivity extends BaseActivity {

    public List<FilterBean> beenList = new ArrayList<>();
    public Bitmap itemBitmap;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fImage)
    FilterImageView fImage;
    @BindView(R.id.matrixSelect)
    RecyclerView matrixSelect;

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFilterImage(fImage);
        initActionBar();

        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        itemBitmap = Utils.readBitmap(this, R.mipmap.damimi, 4);
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
        getFilterDatas();
        matrixSelect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        matrixSelect.setAdapter(new ColorFilterAdapter());

        setImageBitmap();
        setItemBitmap();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("添加滤镜");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            bitmap.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String imageUrl;
    Bitmap bitmap = null;

    private void setImageBitmap() {

        if (alreadyChange) {
            bitmap = BitmapFactory.decodeFile(imageUrl);
        } else {
            bitmap = Utils.readBitmap(imageUrl, 2);
        }
        fImage.setImageBitmap(bitmap);
        fImage.setFloat(ColorMatrixValue.src);
    }

    private void setItemBitmap() {
        itemBitmap = Utils.readBitmap(imageUrl, 5);
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
