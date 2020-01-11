package com.example.generify.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generify.R;
import com.example.generify.databinding.DashboardPlaylistFragmentBinding;
import com.example.generify.util.RecyclerViewItemClickListener;
import com.example.generify.util.ViewModelFactory;
import com.example.generify.view.adapter.SearchTrackAdapter;
import com.example.generify.view.adapter.UserTopTracksAdapter;
import com.example.generify.view.customView.SelectedTrack;
import com.example.generify.viewModel.PlaylistFragmentViewModel;
import com.example.generify.viewModel.ProfileFragmentViewModel;

import java.util.Date;

public class PlaylistFragment extends BaseFragment<PlaylistFragmentViewModel, DashboardPlaylistFragmentBinding> {

    private SearchView searchView;
    private RecyclerView searchTrackRecyclerView;
    private SearchTrackAdapter searchTrackAdapter;
    private CountDownTimer searchCountDown;
    private SelectedTrack selectedTrack;
    private String[] searchText = {""};
    private String[] features = {"energy", "acousticness", "instrumentalness", "valence", "tempo", "speechiness"};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        application = activity.getApplication();

        searchCountDown = new CountDownTimer(300, 300) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                viewModel.searchTrackCall(searchText);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.init(inflater, container);
        return view;
    }

    @Override
    protected void initArguments() {
        if(getArguments() != null){
            sharedPreferences = application.getSharedPreferences(getArguments().getString("sharedPreferences"),
                    Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(activity, new ViewModelFactory(application)).
                get(PlaylistFragmentViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initView() {
        searchView = view.findViewById(R.id.playlist_searchview_id);
        searchTrackRecyclerView = view.findViewById(R.id.search_track_recycler_view);
        selectedTrack = view.findViewById(R.id.dashboard_playlist_selected_track_id);
    }

    @Override
    protected void initListener(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCountDown.cancel();

                if(newText.isEmpty()|| newText == null){
                    searchTrackRecyclerView.setVisibility(View.GONE);
                    selectedTrack.setVisibility(View.VISIBLE);
                }
                else{
                    searchTrackRecyclerView.setVisibility(View.VISIBLE);
                    selectedTrack.setVisibility(View.GONE);
                    searchText[0] = newText;
                    searchCountDown.start();
                }
                return true;
            }
        });
    }

    @Override
    protected void initObserver() {
        viewModel.getSearchTrack().observe(this, searchTracks -> {
            searchTrackAdapter = new SearchTrackAdapter(searchTracks, (v, position) -> {
                selectedTrack.setTrackName(searchTracks.get(position).getTrackName());
                selectedTrack.setAlbumCover(searchTracks.get(position).getAlbumCover());
                selectedTrack.setArtistName(searchTracks.get(position).getArtistName());
                selectedTrack.setTrackId(searchTracks.get(position).getTrackId());
                searchTrackRecyclerView.setVisibility(View.GONE);
                selectedTrack.setVisibility(View.VISIBLE);
                return true;
            });
            searchTrackRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            searchTrackRecyclerView.setAdapter(searchTrackAdapter);
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.dashboard_playlist_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel= null;
        binding = null;
        sharedPreferences = null;
        editor = null;
        view = null;
        searchView = null;
    }
}
