package com.example.generify.service;

import android.os.AsyncTask;

import com.example.generify.util.GenerifyFunction;
import com.example.generify.util.ProgressBarListener;

public class Worker extends AsyncTask<Void, Void, String> {

    private GenerifyFunction.StrFunctionStrStrArr action;
    private String endpoint;
    private String[] param;
    private ProgressBarListener progressBarListener;

    public Worker(GenerifyFunction.StrFunctionStrStrArr action, String endpoint, String[] param){
        this.action = action;
        this.endpoint = endpoint;
        this.param = param;
        this.progressBarListener = null;
    }

    public Worker(GenerifyFunction.StrFunctionStrStrArr action, String endpoint, String[] param, ProgressBarListener progressBarListener){
        this.action = action;
        this.endpoint = endpoint;
        this.param = param;
        this.progressBarListener = progressBarListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(progressBarListener != null){
            progressBarListener.showProgressBar();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        return action.apply(endpoint, param);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressBarListener != null){
            progressBarListener.hideProgressBar();
        }
    }
}
