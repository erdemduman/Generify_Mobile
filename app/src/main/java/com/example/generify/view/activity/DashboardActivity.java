package com.example.generify.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.generify.R;
import com.example.generify.constant.Constants;
import com.example.generify.constant.SharedConstants;
import com.example.generify.databinding.ActivityDashboardBinding;
import com.example.generify.util.FragmentFactory;
import com.example.generify.viewModel.DashboardViewModel;
import com.example.generify.util.ViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class DashboardActivity extends BaseActivity<DashboardViewModel, ActivityDashboardBinding> {

    private BottomNavigationView dashboard_bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        firstRunCheck();
        defaultFragment();
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

                    Log.d("FIRST_TOKEN", token);
                    break;

                case ERROR:
                    Log.d("ERROR", "There is an error here.");
                    break;

                default:
                    Log.d("DEFAULT", "Default value.");
            }
        }
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_dashboard;
    }

    // region Init Methods

    @Override
    protected void initViewModel(){
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication()))
                .get(DashboardViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initView(){
        dashboard_bottom_nav = findViewById(R.id.dashboard_bottom_navigation);
    }

    @Override
    protected void initListener(){
        dashboard_bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                changeFragment(FragmentFactory.getDashboardFragment(menuItem));
                return true;
            }
        });
    }

    @Override
    protected void initObserver(){
        viewModel.getSpotifyAuthRequest().observe(this, request -> {
            try{
                AuthenticationClient.openLoginActivity(this, Constants.REQUEST_CODE, request);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    //endregion

    private void firstRunCheck(){
        boolean first_time = sharedPreferences.getBoolean(SharedConstants.FIRST_RUN, true);
        boolean is_auth = sharedPreferences.getBoolean(SharedConstants.IS_AUTH, false);

        if(first_time){
            editor.putBoolean(SharedConstants.FIRST_RUN, false);
            editor.putBoolean(SharedConstants.IS_AUTH, false);
            editor.apply();
        }
        else if(is_auth){
            viewModel.runSpotifyAuthRequest();
        }
    }

    public void defaultFragment(){
        changeFragment(FragmentFactory.getDashboardFragment());
    }

    public void changeFragment(Fragment fragment) {

        if(fragment != null){
            Bundle bundle = new Bundle();
            bundle.putString("sharedPreferences", SharedConstants.SHARED_PREFERENCE);
            fragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.dashboard_frame, fragment)
                    .commit();
        }
    }

    @Override
    public AppCompatActivity getActivity(){
        return this;
    }

}
