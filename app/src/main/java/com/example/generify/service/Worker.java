package com.example.generify.service;

import android.os.AsyncTask;

import com.example.generify.util.GenerifyFunction;

public class Worker extends AsyncTask<Void, Void, String> {

    private GenerifyFunction.StrFunctionStrStrArr action;
    private String endpoint;
    private String[] param;

    public Worker(GenerifyFunction.StrFunctionStrStrArr action, String endpoint, String[] param){
        this.action = action;
        this.endpoint = endpoint;
        this.param = param;
    }

    @Override
    protected String doInBackground(Void... params) {
        return action.apply(endpoint, param);
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }
}
