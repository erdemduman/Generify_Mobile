package com.example.generify.view.activity;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.generify.constant.SharedConstants;
import com.example.generify.viewModel.BaseViewModel;

public abstract class BaseActivity<T extends BaseViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    protected T viewModel;
    protected B binding;
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    protected void init() {
        binding = DataBindingUtil.setContentView(getActivity(), getLayoutResourceId());

        sharedPreferences = getApplicationContext().getSharedPreferences(SharedConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        initViewModel();
        initView();
        initListener();
        initObserver();

    }

    protected abstract void initViewModel();
    protected abstract void initView();
    protected abstract void initObserver();
    protected abstract void initListener();

    protected abstract AppCompatActivity getActivity();
    protected abstract int getLayoutResourceId();
}
