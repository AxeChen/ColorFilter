package com.mg.axe.colorfilter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mg.axe.colorfilter.R;
import com.mg.axe.colorfilter.constant.ColorMatrixValue;
import com.mg.axe.colorfilter.filter.FilterImageView;
import com.mg.axe.colorfilter.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author Zaifeng
 * @Create 2017/7/25 0025
 * @Description Content
 */

public class ColorMatrixSetActivity extends BaseActivity {

    @BindView(R.id.a)
    EditText ea;
    @BindView(R.id.b)
    EditText eb;
    @BindView(R.id.c)
    EditText ec;
    @BindView(R.id.d)
    EditText ed;
    @BindView(R.id.e)
    EditText ee;
    @BindView(R.id.f)
    EditText ef;
    @BindView(R.id.g)
    EditText eg;
    @BindView(R.id.h)
    EditText eh;
    @BindView(R.id.i)
    EditText ei;
    @BindView(R.id.j)
    EditText ej;
    @BindView(R.id.k)
    EditText ek;
    @BindView(R.id.l)
    EditText el;
    @BindView(R.id.m)
    EditText em;
    @BindView(R.id.n)
    EditText en;
    @BindView(R.id.o)
    EditText eo;
    @BindView(R.id.p)
    EditText ep;
    @BindView(R.id.q)
    EditText eq;
    @BindView(R.id.r)
    EditText er;
    @BindView(R.id.s)
    EditText es;
    @BindView(R.id.t)
    EditText et;
    @BindView(R.id.adjustView)
    FilterImageView adjustView;
    @BindView(R.id.btnRest)
    Button btnRest;
    @BindView(R.id.btnApply)
    Button btnApply;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.llMatrix)
    LinearLayout llMatrix;

    private ActionBar bar;

    private List<EditText> editTexts = new ArrayList<>();

    private float a = 1f, b, c, d, e,
            f, g = 1f, h, i, j,
            k, l, m = 1f, n, o,
            p, q, r, s = 1f, t;

    private float[] floats = {
            a, b, c, d, e,
            f, g, h, i, j,
            k, l, m, n, o,
            p, q, r, s, t,
    };

    private float[] colorMatrixValue = new float[20];


    private void initActionBar() {
        setSupportActionBar(toolbar);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("自定义矩阵值");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_adjust);
        imageUrl = getIntent().getStringExtra(TAG_STRING_IMAGE_URL);
        ButterKnife.bind(this);
        setFilterImage(adjustView);

        setDefaultValue();
        initEditTextList();
        initListener();
        initActionBar();
        setImageBitmap();
    }

    private String imageUrl;

    private void setImageBitmap() {
        Bitmap bitmap = null;
        if (alreadyChange) {
            bitmap = BitmapFactory.decodeFile(imageUrl);
        } else {
            bitmap = Utils.readBitmap(imageUrl, 2);
        }
        adjustView.setImageBitmap(bitmap);
        adjustView.setFloat(ColorMatrixValue.src);
    }

    private void initEditTextList() {
        editTexts.add(ea);
        editTexts.add(eb);
        editTexts.add(ec);
        editTexts.add(ed);
        editTexts.add(ee);
        editTexts.add(ef);
        editTexts.add(eg);
        editTexts.add(eh);
        editTexts.add(ei);
        editTexts.add(ej);
        editTexts.add(ek);
        editTexts.add(el);
        editTexts.add(em);
        editTexts.add(en);
        editTexts.add(eo);
        editTexts.add(ep);
        editTexts.add(eq);
        editTexts.add(ei);
        editTexts.add(es);
        editTexts.add(et);
    }

    public void initListener() {
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
            }
        });

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void apply() {

        for (int i = 0; i < editTexts.size(); i++) {
            try {
                colorMatrixValue[i] = Float.parseFloat(editTexts.get(i).getText().toString());
            } catch (NumberFormatException e1) {
                Toast.makeText(this, "是否输入了正确的数字", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        adjustView.setFloat(colorMatrixValue);
    }

    private void reset() {
        a = 1f;
        b = 0f;
        c = 0f;
        d = 0f;
        e = 0f;
        f = 0f;
        g = 1f;
        h = 0f;
        i = 0f;
        j = 0f;
        k = 0f;
        l = 0f;
        m = 1f;
        n = 0f;
        o = 0f;
        p = 0f;
        q = 0f;
        r = 0;
        s = 1f;
        t = 0f;
        setDefaultValue();
        adjustView.setFloat(floats);
    }


    public void setDefaultValue() {
        ea.setText(a + "");
        eb.setText(b + "");
        ec.setText(c + "");
        ed.setText(d + "");
        ee.setText(e + "");
        ef.setText(f + "");
        eg.setText(g + "");
        eh.setText(h + "");
        ei.setText(i + "");
        ej.setText(j + "");
        ek.setText(k + "");
        el.setText(l + "");
        em.setText(m + "");
        en.setText(n + "");
        eo.setText(o + "");
        ep.setText(p + "");
        eq.setText(q + "");
        er.setText(r + "");
        es.setText(s + "");
        et.setText(t + "");

    }

}
