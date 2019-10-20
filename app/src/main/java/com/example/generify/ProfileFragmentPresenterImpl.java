package com.example.generify;

import androidx.fragment.app.Fragment;

import com.example.generify.View.IProfileFragmentView;

public class ProfileFragmentPresenterImpl {

    IProfileFragmentView fragment;

    public ProfileFragmentPresenterImpl(IProfileFragmentView fragment){
        this.fragment = fragment;
    }

    public void showText(){
        fragment.showText("LOOOL");
    }
}
