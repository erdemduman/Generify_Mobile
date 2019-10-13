package com.example.generify;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import com.example.generify.Fragment.HomeFragment;
import com.example.generify.Fragment.NotificationsFragment;
import com.example.generify.Fragment.PlaylistFragment;
import com.example.generify.Fragment.ProfileFragment;
import com.example.generify.View.IDashboardView;

public class DashboardPresenterImpl {

    private IDashboardView view;

    public DashboardPresenterImpl(IDashboardView view){
        this.view = view;
    }

    public void handleFragment(MenuItem item){

        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.dashboard_bottom_nav_home:
                fragment = new HomeFragment();
                break;

            case R.id.dashboard_bottom_nav_playlist:
                fragment = new PlaylistFragment();
                break;

            case R.id.dashboard_bottom_nav_notifications:
                fragment = new NotificationsFragment();
                break;

            case R.id.dashboard_bottom_nav_profile:
                fragment = new ProfileFragment();
                break;
        }

        view.changeFragment(fragment);
    }

}
