package com.example.generify.service;

import android.app.Application;
import android.util.Log;

import com.example.generify.constant.Constants;
import com.example.generify.constant.ServiceDictionary;
import com.example.generify.constant.SharedConstants;
import com.example.generify.model.UserTopTrack;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class UserService extends BaseService {

    private String userTopTracks;

    public UserService(Application application){
        super(application);
        serviceDictionary.put(ServiceDictionary.USER_TOP_TRACKS, this::runUserTopTracks);
    }

    private String runUserTopTracks(){
        HttpsURLConnection conn;
        String returnResponse = "";
        URL obj;
        String endpoint = "https://api.spotify.com/v1/me/top/tracks";

        try{
            obj = new URL(endpoint);
            conn = (HttpsURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", Constants.USER_AGENT);

            String auth = "Bearer " + sharedPreferences.getString(SharedConstants.AUTH_TOKEN, "");
            conn.setRequestProperty("Authorization", auth);

            conn.connect();

            //for debug
            int code = conn.getResponseCode();

            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            conn.disconnect();

            Log.d("RESPONSE", response.toString());

            returnResponse = response.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return returnResponse;
    }

    public String getUserTopTracks(){
        return userTopTracks;
    }
}
