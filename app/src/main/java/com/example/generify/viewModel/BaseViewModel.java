package com.example.generify.viewModel;


import android.app.Application;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    private Application application;

    public BaseViewModel(Application application){
        this.application = application;
    }

    protected Application getApplication(){
        return this.application;
    }
}
