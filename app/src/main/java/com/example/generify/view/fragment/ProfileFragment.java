package com.example.generify.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.generify.R;
import com.example.generify.databinding.DashboardProfileFragmentBinding;
import com.example.generify.constant.Constants;
import com.example.generify.constant.SharedConstants;
import com.example.generify.viewModel.ProfileFragmentViewModel;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.function.Consumer;
import java.util.function.Function;

public class ProfileFragment extends BaseFragment {

    private DashboardProfileFragmentBinding binding;
    private ProfileFragmentViewModel viewModel;
    private View view;
    private View auth_view;
    private View non_auth_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_profile_fragment, container, false);
        viewModel = new ProfileFragmentViewModel();
        binding.setViewModel(viewModel);
        view = binding.getRoot();

        initArguments();
        initView();
        initObserver();
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
                    Log.d("ERROR", "There is an error here.");
                    break;

                default:
                    Log.d("DEFAULT", "Default value.");
            }
        }
    }

    //region Init Methods

    private void initArguments(){
        if(getArguments() != null){
            sharedPreferences = getActivity().
                    getApplicationContext().
                    getSharedPreferences(getArguments().getString("sharedPreferences"), Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    private void initView(){
        auth_view = view.findViewById(R.id.profile_fragment_auth_id);
        non_auth_view = view.findViewById(R.id.profile_fragment_nonauth_id);
    }

    private void initObserver(){
        viewModel.getSpotifyAuthRequest().observe(this, request -> {
            try {
                Intent intent = AuthenticationClient.createLoginActivityIntent(getActivity(), request);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        });
    }

    private void initPage(){
        if(sharedPreferences.getBoolean(SharedConstants.IS_AUTH, false)){
            viewModel.spotifyAuthRequest();
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