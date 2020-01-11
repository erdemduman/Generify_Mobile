package com.example.generify.view.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.generify.R;
import com.example.generify.command.ICommand;
import com.example.generify.databinding.SelectedTrackBinding;
import com.squareup.picasso.Picasso;

public class SelectedTrack extends LinearLayout {

    private SelectedTrackBinding binding;

    private String albumCover;
    private String trackName;
    private String artistName;
    private String spinnerString;
    private ICommand generateCommand;
    private String trackId;

    private ImageView albumCoverView;
    private TextView trackNameView;
    private TextView artistNameView;
    private Spinner featureSpinner;
    private Button generateButton;

    public SelectedTrack(Context context) {
        super(context);
    }

    public SelectedTrack(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.selected_track, this, true);
        initView();
    }

    public void initView(){
        albumCoverView = findViewById(R.id.selected_track_album_cover_id);
        trackNameView = findViewById(R.id.selected_track_track_id);
        artistNameView = findViewById(R.id.selected_track_artist_id);
        featureSpinner = findViewById(R.id.selected_track_spinner_id);
        generateButton = findViewById(R.id.selected_track_generate_button_id);
    }

    public void setAlbumCover(String albumCover){
        this.albumCover = albumCover;
        Picasso.get().load(this.albumCover).into(albumCoverView);
    }

    public void setTrackName(String trackName){
        this.trackName = trackName;
        trackNameView.setText(this.trackName);
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
        artistNameView.setText(this.artistName);
    }

    public void setSpinnerString(String spinnerString){
        this.spinnerString = spinnerString;
    }

    public void setGenerateCommand(ICommand generateCommand){
        this.generateCommand = generateCommand;
    }

    public void setTrackId(String trackId){
        this.trackId = trackId;
    }
}
