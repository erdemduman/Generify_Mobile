package com.example.generify.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generify.R;
import com.example.generify.databinding.DashboardProfileFragmentBinding;
import com.example.generify.constant.Constants;
import com.example.generify.constant.SharedConstants;
import com.example.generify.util.ViewModelFactory;
import com.example.generify.view.adapter.UserTopTracksAdapter;
import com.example.generify.viewModel.ProfileFragmentViewModel;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.function.Consumer;
import java.util.function.Function;

public class ProfileFragment extends BaseFragment<ProfileFragmentViewModel, DashboardProfileFragmentBinding> {

    private View auth_view;
    private View non_auth_view;
    private UserTopTracksAdapter userTopTracksAdapter;
    private RecyclerView userTopTracksRecyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        application = activity.getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.init(inflater, container);
        initPage();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) throws NullPointerException{
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
            switch (response.getType()) {
                case TOKEN:
                    String token = response.getAccessToken();
                    if(editor != null){
                        editor.putString(SharedConstants.AUTH_TOKEN, token);
                        editor.putBoolean(SharedConstants.IS_AUTH, true);
                        editor.apply();
                    }
                    else
                        throw new NullPointerException();

                    Log.d("TOKEN", token);
                    initPage();
                    break;

                case ERROR:
                    Log.d("ERROR", response.getError());
                    break;

                default:
                    Log.d("DEFAULT", "Default value.");
            }
        }
    }

    //region Init Methods

    @Override
    protected void initArguments(){
        if(getArguments() != null){
            sharedPreferences = application.getSharedPreferences(getArguments().getString("sharedPreferences"),
                    Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    @Override
    protected void initViewModel(){
        viewModel = ViewModelProviders.of(activity, new ViewModelFactory(application)).
                get(ProfileFragmentViewModel.class);
        binding.setViewModel(viewModel);
    }


    @Override
    protected void initView(){
        auth_view = view.findViewById(R.id.profile_fragment_auth_id);
        non_auth_view = view.findViewById(R.id.profile_fragment_nonauth_id);
        userTopTracksRecyclerView = view.findViewById(R.id.top_tracks_recycler_view);
    }

    @Override
    protected void initListener(){

    }

    @Override
    protected void initObserver(){
        viewModel.getSpotifyAuthRequest().observe(this, request -> {
            try {
                Intent intent = AuthenticationClient.createLoginActivityIntent(activity, request);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        });

        viewModel.getUserTopTracks().observe(this, userTopTracks -> {
            userTopTracksAdapter = new UserTopTracksAdapter(userTopTracks);
            userTopTracksRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            userTopTracksRecyclerView.setAdapter(userTopTracksAdapter);
        });
    }

    private void initPage(){
        if(sharedPreferences.getBoolean(SharedConstants.IS_AUTH, false)){
            auth_view.setVisibility(View.VISIBLE);
            non_auth_view.setVisibility(View.GONE);
        }
        else{
            auth_view.setVisibility(View.GONE);
            non_auth_view.setVisibility(View.VISIBLE);
        }
    }

    //endregion

    @Override
    protected int getLayoutResourceId() {
        return R.layout.dashboard_profile_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel= null;
        binding = null;
        sharedPreferences = null;
        editor = null;
        view = null;
        auth_view = null;
        non_auth_view = null;
    }
}