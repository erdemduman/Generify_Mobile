package com.example.generify.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.generify.constant.ServiceDictionaryEnum;
import com.example.generify.constant.SharedConstants;
import com.example.generify.util.GenerifyFunction;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSevice {
    protected Map<ServiceDictionaryEnum, GenerifyFunction.ServiceFunction> serviceDictionary;
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    protected BaseSevice(Context context){
        serviceDictionary = new HashMap<>();
        sharedPreferences = context.getSharedPreferences(SharedConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    protected GenerifyFunction.ServiceFunction getMethod(ServiceDictionaryEnum key){
        return serviceDictionary.get(key);
    }
}
