package com.example.generify.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.service.SpotifyAuth;
import com.spotify.sdk.android.authentication.AuthenticationRequest;

public class DashboardViewModel extends BaseViewModel {

    private MutableLiveData<AuthenticationRequest> spotifyAuthRequestProp;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        spotifyAuthRequestProp = new MutableLiveData<>();
    }

    public void runSpotifyAuthRequest(){
        spotifyAuthRequestProp.setValue(SpotifyAuth.getAuthRequest());
    }

    public LiveData<AuthenticationRequest> getSpotifyAuthRequest(){
        return spotifyAuthRequestProp;
    }



}
