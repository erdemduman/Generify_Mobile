package com.example.generify.viewModel;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.Command;
import com.example.generify.service.SpotifyAuth;
import com.spotify.sdk.android.authentication.AuthenticationRequest;

public class ProfileFragmentViewModel extends BaseViewModel {

    private MutableLiveData<AuthenticationRequest> spotifyAuthRequest;

    @Bindable
    public String profileText;

    @Bindable
    public Command backCmd;

    public ProfileFragmentViewModel(){
        spotifyAuthRequest = new MutableLiveData<>();
        initCmd();
    }

    private void initCmd(){
        setBackCmd();
    }

    public void onClickLoginToSpotify(){
        spotifyAuthRequest();
    }

    public void spotifyAuthRequest(){
        setSpotifyAuthRequest();
    }

    private void setBackCmd(){
        backCmd = new Command() {
            @Override
            public void execute() {
                if(canExecute()){
                    Log.d("TAG", "DOOGRU AQQ");
                }
            }
            @Override
            public void execute(Object param) { }
            @Override
            public boolean canExecute() {
                return true;
            }
        };
        notifyPropertyChanged(BR.backCmd);
    }

    public Command getBackCmd(){
        return backCmd;
    }

    private void setSpotifyAuthRequest(){
        spotifyAuthRequest.setValue(SpotifyAuth.getAuthRequest());
    }

    public MutableLiveData<AuthenticationRequest> getSpotifyAuthRequest(){
        return spotifyAuthRequest;
    }

}
