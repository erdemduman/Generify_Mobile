package com.example.generify.view.fragment;

import android.app.Application;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.generify.viewModel.BaseViewModel;

public abstract class BaseFragment<T extends BaseViewModel, B extends ViewDataBinding> extends Fragment {

    protected FragmentActivity activity;
    protected Application application;
    protected View view;
    protected T viewModel;
    protected B binding;
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    protected void init(@NonNull LayoutInflater inflater, @Nullable ViewGroup container){
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();

        initArguments();
        initViewModel();
        initView();
        initListener();
        initObserver();
    }

    //init
    protected abstract void initArguments();
    protected abstract void initViewModel();
    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initObserver();

    //get
    protected abstract int getLayoutResourceId();
}
