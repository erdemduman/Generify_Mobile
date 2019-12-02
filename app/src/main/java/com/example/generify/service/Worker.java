package com.example.generify.service;

import android.os.AsyncTask;

import com.example.generify.util.GenerifyFunction;

public class Worker extends AsyncTask<Void, Void, String> {

    private GenerifyFunction.StringFunction action;

    public Worker(GenerifyFunction.StringFunction action){
        this.action = action;
    }

    @Override
    protected String doInBackground(Void... params) {
        return action.apply();
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }
}
