package com.example.generify.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.generify.viewModel.DashboardViewModel;
import com.example.generify.viewModel.PlaylistFragmentViewModel;
import com.example.generify.viewModel.ProfileFragmentViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private ProgressBarListener progressBarListener;

    public ViewModelFactory(Application application){
        this.application = application;
    }

    public ViewModelFactory(Application application, ProgressBarListener progressBarListener){
        this.application = application;
        this.progressBarListener = progressBarListener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass == DashboardViewModel.class)
            return (T) new DashboardViewModel(application);
        else if(modelClass == ProfileFragmentViewModel.class)
            return (T) new ProfileFragmentViewModel(application);
        else if(modelClass == PlaylistFragmentViewModel.class)
            return (T) new PlaylistFragmentViewModel(application, progressBarListener);
        else
            return super.create(modelClass);
    }
}
