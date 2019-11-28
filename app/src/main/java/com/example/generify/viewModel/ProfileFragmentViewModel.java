package com.example.generify.viewModel;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.command.Command;
import com.example.generify.command.ICommand;
import com.example.generify.service.SpotifyAuth;
import com.spotify.sdk.android.authentication.AuthenticationRequest;

public class ProfileFragmentViewModel extends BaseViewModel {

    private MutableLiveData<AuthenticationRequest> spotifyAuthRequestProp;
    public String profileText;
    private ICommand back;

    public ProfileFragmentViewModel(@NonNull Application application){
        super(application);
        spotifyAuthRequestProp = new MutableLiveData<>();
        initCmd();
    }

    private void initCmd(){
        back = new Command<Void>(this::cmdBack);
    }

    public void onClickLoginToSpotify(){
        runSpotifyAuthRequest();
    }

    public void runSpotifyAuthRequest(){
        spotifyAuthRequestProp.setValue(SpotifyAuth.getAuthRequest());
    }

    public ICommand getBackCmd(){
        return back;
    }

    public LiveData<AuthenticationRequest> getSpotifyAuthRequest(){
        return spotifyAuthRequestProp;
    }

    private void cmdBack(Void param){
        Log.d("TAG", "DOOGRU AQQ");
    }

}
