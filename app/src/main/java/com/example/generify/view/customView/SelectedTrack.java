package com.example.generify.view.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.generify.R;
import com.example.generify.command.ICommand;
import com.example.generify.databinding.SelectedTrackBinding;
import com.example.generify.util.parameter.GenerateParameter;
import com.squareup.picasso.Picasso;

public class SelectedTrack extends LinearLayout {

    private SelectedTrackBinding binding;

    private String albumCover;
    private String trackName;
    private String artistName;
    private String feature;
    private ICommand generateCommand = null;
    private String trackId;
    private String[] features = {"energy", "acousticness", "instrumentalness", "valence", "tempo", "speechiness"};

    private ImageView albumCoverView;
    private TextView trackNameView;
    private TextView artistNameView;
    private Spinner featureSpinner;
    private Button generateButton;

    private MutableLiveData<Boolean> generateButtonClicked;

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

        generateButtonClicked = new MutableLiveData<>();
        generateButtonClicked.setValue(false);

        initView(context);
        initListener();
    }

    public void initView(Context context){
        albumCoverView = findViewById(R.id.selected_track_album_cover_id);
        trackNameView = findViewById(R.id.selected_track_track_id);
        artistNameView = findViewById(R.id.selected_track_artist_id);

        featureSpinner = findViewById(R.id.selected_track_spinner_id);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, features);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        featureSpinner.setAdapter(adapter);

        generateButton = findViewById(R.id.selected_track_generate_button_id);
    }

    public void initListener(){
        featureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                feature = parent.getItemAtPosition(0).toString();
            }
        });

        generateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generateCommand != null && generateCommand.canExecute()){
                    generateCommand.execute(new GenerateParameter(trackId, feature));
                    generateButtonClicked.setValue(true);
                }
            }
        });
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

    public void setGenerateCommand(ICommand generateCommand){
        this.generateCommand = generateCommand;
    }

    public void setTrackId(String trackId){
        this.trackId = trackId;
    }

    public void setGenerateButtonClicked(Boolean bool){
        generateButtonClicked.setValue(bool);
    }

    public LiveData<Boolean> getGenerateButtonClicked(){
        return generateButtonClicked;
    }
}
