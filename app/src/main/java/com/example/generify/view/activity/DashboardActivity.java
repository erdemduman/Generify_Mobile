package com.example.generify.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.generify.R;
import com.example.generify.constant.SharedConstants;
import com.example.generify.view.util.FragmentFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity {

    private BottomNavigationView dashboard_bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();

        firstRunCheck();
        defaultFragment();
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_dashboard;
    }

    // region Init Methods

    private void initView(){
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

    //endregion

    private void firstRunCheck(){
        sharedPreferences = getApplicationContext().getSharedPreferences(SharedConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean first_time = sharedPreferences.getBoolean(SharedConstants.FIRST_RUN, true);

        if(first_time){
            editor.putBoolean(SharedConstants.FIRST_RUN, false);
            editor.putBoolean(SharedConstants.IS_AUTH, false);
            editor.apply();
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
}
