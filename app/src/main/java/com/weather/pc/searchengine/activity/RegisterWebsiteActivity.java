package com.weather.pc.searchengine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.weather.pc.searchengine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterWebsiteActivity extends AppCompatActivity {

    @BindView(R.id.imv_keyback_registernew_website)
    ImageView imv_keyback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_website);
        ButterKnife.bind(this);

        imv_keyback.setOnClickListener(v->{
            finish();
        });

    }
}
