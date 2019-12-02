package com.example.generify.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generify.R;
import com.example.generify.model.UserTopTrack;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserTopTracksAdapter extends RecyclerView.Adapter<UserTopTracksAdapter.ViewHolder> {

    private List<UserTopTrack> userTopTrackList;

    public UserTopTracksAdapter(List<UserTopTrack> userTopTrackList){
        this.userTopTrackList = userTopTrackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_top_songs, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(userTopTrackList.get(position).getAlbumCover()).into(holder.albumCover);
        holder.artistName.setText(userTopTrackList.get(position).getArtistName());
        holder.albumName.setText(userTopTrackList.get(position).getAlbumName());
        holder.songName.setText(userTopTrackList.get(position).getSongName());
    }

    @Override
    public int getItemCount() {
        return userTopTrackList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView albumCover;
        public TextView songName;
        public TextView albumName;
        public TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.card_top_songs_album_cover_id);
            songName = itemView.findViewById(R.id.card_top_songs_track_name_id);
            albumName = itemView.findViewById(R.id.card_top_songs_album_id);
            artistName = itemView.findViewById(R.id.card_top_songs_artist_id);
        }
    }
}
