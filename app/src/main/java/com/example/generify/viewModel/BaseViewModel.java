package com.example.generify.viewModel;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    private Application application;
    protected MutableLiveData<Boolean> showPopup;

    public BaseViewModel(Application application){
        this.application = application;
        showPopup = new MutableLiveData<>();
    }

    protected Application getApplication(){
        return this.application;
    }

    public LiveData<Boolean> getShowPopup(){
        return showPopup;
    }
}
