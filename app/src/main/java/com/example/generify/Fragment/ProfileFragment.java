package com.example.generify.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.generify.ProfileFragmentPresenterImpl;
import com.example.generify.R;
import com.example.generify.View.IProfileFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment implements IProfileFragmentView {

    //region Data Fields

    @BindView(R.id.textlol) TextView textView;
    private ProfileFragmentPresenterImpl presenter;
    private Unbinder unbinder;

    //endregion

    //region Fragment Methods

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dashboard_profile_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new ProfileFragmentPresenterImpl(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //endregion

    //region Listeners

    @OnClick(R.id.profile_login_spotify_button)
    public void onClickSpotifyButton(){
        presenter.showText();
    }

    //endregion

    //region View Methods

    @Override
    public void showText(String text) {
        textView.setText(text);
    }

    //endregion


}