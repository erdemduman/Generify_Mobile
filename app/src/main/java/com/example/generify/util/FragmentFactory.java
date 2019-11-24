package com.example.generify.util;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import com.example.generify.R;
import com.example.generify.view.fragment.HomeFragment;
import com.example.generify.view.fragment.NotificationsFragment;
import com.example.generify.view.fragment.PlaylistFragment;
import com.example.generify.view.fragment.ProfileFragment;

public class FragmentFactory {
    public static Fragment getDashboardFragment(MenuItem item){
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
        return fragment;
    }

    public static Fragment getDashboardFragment(){
        return new HomeFragment();
    }
}
