package com.example.generify.service;

import android.app.Application;
import android.util.Log;
import android.util.Pair;

import com.example.generify.constant.Constants;
import com.example.generify.constant.ServiceDictionary;
import com.example.generify.constant.SharedConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiService extends BaseService {

    public ApiService(Application application){
        super(application);
        serviceDictionary.put(
                ServiceDictionary.USER_TOP_TRACKS,
                new Pair<>("https://api.spotify.com/v1/me/top/tracks",this::callAuth)
        );
        serviceDictionary.put(ServiceDictionary.SEARCH,
                new Pair<>("https://api.spotify.com/v1/search", this::callSearch)
        );
        serviceDictionary.put(ServiceDictionary.GENERATE,
                new Pair<>("http://34.70.26.7:8080", this::callGenerate));
    }

    private String callAuth(String endpoint, String[] param){
        HttpsURLConnection conn;
        String returnResponse = "";
        URL obj;

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

    private String callSearch(String endpoint, String[] param){
        HttpsURLConnection conn;
        String returnResponse = "";
        URL obj;

        try{
            String query = param[0].replace(" ", "%20");
            endpoint += "?q=" + query + "&type=track&limit=10";

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

    private String callGenerate(String endpoint, String[] param){
        HttpURLConnection conn;
        String returnResponse = "";
        URL obj;

        try{
            endpoint += "/" + param[0] + "/" + param[1];

            obj = new URL(endpoint);
            conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", Constants.USER_AGENT);

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
}
