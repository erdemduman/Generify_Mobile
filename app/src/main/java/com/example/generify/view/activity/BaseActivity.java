package com.example.generify.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();
    protected abstract void initListener();
}
