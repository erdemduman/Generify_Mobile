package com.example.generify.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.generify.constant.ServiceDictionaryEnum;
import com.example.generify.constant.SharedConstants;
import com.example.generify.util.GenerifyCallback;
import com.example.generify.util.GenerifyFunction;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseSevice {

    private String userTopTracksResponse;

    public UserService(Context context){
        super(context);
        serviceDictionary.put(ServiceDictionaryEnum.USER_TOP_TRACKS, this::userTopTracks);
    }

    public void userTopTracks(GenerifyCallback callback){
        String endpoint = "https://api.spotify.com/v1/me/top/tracks";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {

                    callback.onSuccess();
                }, error -> {
                    Log.d("Error", "userTopTracks");

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString(SharedConstants.AUTH_TOKEN, "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

    }

    public String getUserTopTracks(){
        return userTopTracksResponse;
    }
}
