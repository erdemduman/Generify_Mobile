package com.example.generify.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.command.Command;
import com.example.generify.command.ICommand;
import com.example.generify.constant.ServiceDictionary;
import com.example.generify.model.GeneratePlaylist;
import com.example.generify.util.ProgressBarListener;
import com.example.generify.util.parameter.GenerateParameter;
import com.example.generify.model.SearchTrack;
import com.example.generify.service.ApiService;
import com.example.generify.service.Worker;
import com.example.generify.util.GenerifyFunction;
import com.example.generify.util.parser.GenerateParser;
import com.example.generify.util.parser.SearchParser;

import java.util.List;

public class PlaylistFragmentViewModel extends BaseViewModel {

    private String searchTrackRaw;
    private String generatedPlaylistRaw;
    private MutableLiveData<List<SearchTrack>> searchTrack;
    private MutableLiveData<List<GeneratePlaylist>> generatedPlaylist;
    private ApiService apiService;
    private ICommand cmdGenerate;
    private ProgressBarListener progressBarListener;

    public PlaylistFragmentViewModel(Application application) {
        super(application);
        apiService = new ApiService(getApplication());
        searchTrack = new MutableLiveData<>();
        generatedPlaylist = new MutableLiveData<>();
        cmdGenerate = new Command<GenerateParameter>(this::cmdGenerateCall);
        serviceCall();
    }

    public PlaylistFragmentViewModel(Application application, ProgressBarListener progressBarListener) {
        super(application);
        this.progressBarListener = progressBarListener;
        apiService = new ApiService(getApplication());
        searchTrack = new MutableLiveData<>();
        generatedPlaylist = new MutableLiveData<>();
        cmdGenerate = new Command<GenerateParameter>(this::cmdGenerateCall);
        serviceCall();
    }

    private void serviceCall(){

    }

    public void searchTrackCall(String[] param){
        try{
            String endpoint = apiService.getMethod(ServiceDictionary.SEARCH).first;
            GenerifyFunction.StrFunctionStrStrArr action = apiService.getMethod(ServiceDictionary.SEARCH).second;
            searchTrackRaw = new Worker(action, endpoint, param).execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }

        new Thread(() -> {
            searchTrack.postValue(SearchParser.searchTrackParser(searchTrackRaw));
        }).start();
    }

    public void cmdGenerateCall(GenerateParameter generateParameter){
        try{
            String endpoint = apiService.getMethod(ServiceDictionary.GENERATE).first;
            GenerifyFunction.StrFunctionStrStrArr action = apiService.getMethod(ServiceDictionary.GENERATE).second;
            String uri = "spotify:track:" + generateParameter.getTrackId();
            String feature = generateParameter.getFeature();
            generatedPlaylistRaw = new Worker(action, endpoint, new String[]{uri, feature}, progressBarListener).execute().get();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            generatedPlaylist.postValue(GenerateParser.generatePlaylistParser(generatedPlaylistRaw));
        }
    }

    public ICommand getCmdGenerate(){
        return cmdGenerate;
    }

    public LiveData<List<SearchTrack>> getSearchTrack(){
        return searchTrack;
    }

    public LiveData<List<GeneratePlaylist>> getGeneratedPlaylist(){
        return generatedPlaylist;
    }
}
