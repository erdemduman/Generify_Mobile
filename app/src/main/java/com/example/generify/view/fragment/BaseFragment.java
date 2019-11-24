package com.example.generify.view.fragment;

import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;
}
