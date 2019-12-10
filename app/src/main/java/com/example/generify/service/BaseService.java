package com.example.generify.service;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.example.generify.constant.ServiceDictionary;
import com.example.generify.constant.SharedConstants;
import com.example.generify.util.GenerifyFunction;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseService {
    protected Map<ServiceDictionary, Pair<String,GenerifyFunction.StrFunctionStrStrArr>> serviceDictionary;
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    protected BaseService(Application application){
        serviceDictionary = new HashMap<>();
        sharedPreferences = application.getSharedPreferences(SharedConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public Pair<String,GenerifyFunction.StrFunctionStrStrArr> getMethod(ServiceDictionary key){
        return serviceDictionary.get(key);
    }
}
