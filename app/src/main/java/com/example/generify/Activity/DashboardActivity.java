package com.example.generify.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.generify.DashboardPresenterImpl;
import com.example.generify.R;
import com.example.generify.View.IDashboardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements IDashboardView {

    DashboardPresenterImpl dashboardPresenter;
    @BindView(R.id.dashboard_bottom_navigation) BottomNavigationView dashboard_bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        dashboardPresenter = new DashboardPresenterImpl(this);
        dashboardPresenter.defaultFragment();

        dashboard_bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                dashboardPresenter.handleFragment(menuItem);
                return true;
            }
        });
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_dashboard;
    }


    @Override
    public void changeFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.dashboard_frame, fragment)
                    .commit();
        }
    }
}
