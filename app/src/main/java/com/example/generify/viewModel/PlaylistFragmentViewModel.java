package com.example.generify.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.constant.ServiceDictionary;
import com.example.generify.model.SearchTrack;
import com.example.generify.service.ApiService;
import com.example.generify.service.Worker;
import com.example.generify.util.GenerifyFunction;
import com.example.generify.util.parser.SearchParser;

import java.util.List;

public class PlaylistFragmentViewModel extends BaseViewModel {

    private String searchTrackRaw;
    private MutableLiveData<List<SearchTrack>> searchTrack;
    private ApiService apiService;

    public PlaylistFragmentViewModel(Application application) {
        super(application);
        apiService = new ApiService(getApplication());
        searchTrack = new MutableLiveData<>();
        serviceCall();
    }

    private void serviceCall(){


    }

    public void searchTrackCall(String[] param){
        try{
            String endpoint = apiService.getMethod(ServiceDictionary.SEARCH).first;
            GenerifyFunction.StrFunctionStrStrArr action = apiService.getMethod(ServiceDictionary.SEARCH).second;
            searchTrackRaw = new Worker(action, endpoint, param).execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(() -> {
            searchTrack.postValue(SearchParser.searchTrackParser(searchTrackRaw));
        }).start();
    }

    public LiveData<List<SearchTrack>> getSearchTrack(){
        return searchTrack;
    }
}
