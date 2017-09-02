package demo.aacreations.aacustomfont;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aacreations.aacustomfont.AACustomFont;

/**
 *
 * Created by muhammad.ahsan on 04/05/2017 in AACustomFontDemo.
 * See more at https://github.com/EngrAhsanAli
 * Copyright (C) 2017 AA-Creations.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AACustomFont.getInstance(this)
                .setAlias("myfont", "Budidaya-Regular.otf")
                .setDefaultFontName("myfont");
        DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}
