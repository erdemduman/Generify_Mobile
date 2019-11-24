package com.example.generify.service;

import com.example.generify.constant.Constants;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SpotifyAuth {

    public static AuthenticationRequest getAuthRequest(){
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(Constants.CLIENT_ID,
                        AuthenticationResponse.Type.TOKEN, Constants.REDIRECT_URI);

        builder.setScopes(new String[]{"user-read-private", "user-read-email", "streaming"});
        AuthenticationRequest request = builder.build();

        return request;
    }
}
