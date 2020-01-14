package com.example.generify.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generify.R;
import com.example.generify.model.GeneratePlaylist;
import com.example.generify.util.RecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class GeneratedPlaylistAdapter extends RecyclerView.Adapter<GeneratedPlaylistAdapter.ViewHolder> {

    private List<GeneratePlaylist> generatedPlaylist;
    private RecyclerViewItemClickListener playClickListener;

    public GeneratedPlaylistAdapter(List<GeneratePlaylist> generatedPlaylist, RecyclerViewItemClickListener playClickListener){
        this.generatedPlaylist = generatedPlaylist;
        this.playClickListener = playClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_generated_playlist, parent, false);
        final GeneratedPlaylistAdapter.ViewHolder viewHolder = new GeneratedPlaylistAdapter.ViewHolder(v, playClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(generatedPlaylist.get(position).getAlbumCover()).into(holder.albumCover);
        holder.artistName.setText(generatedPlaylist.get(position).getArtistName());
        holder.trackName.setText(generatedPlaylist.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return generatedPlaylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView albumCover;
        public TextView trackName;
        public TextView artistName;
        public ImageView playButton;
        private WeakReference<RecyclerViewItemClickListener> playClickListenerRef;

        public ViewHolder(@NonNull View itemView, RecyclerViewItemClickListener playClickListener) {
            super(itemView);
            this.playClickListenerRef = new WeakReference<>(playClickListener);

            albumCover = itemView.findViewById(R.id.card_generated_playlist_album_cover_id);
            trackName = itemView.findViewById(R.id.card_generated_playlist_track_name_id);
            artistName = itemView.findViewById(R.id.card_generated_playlist_artist_id);
            playButton = itemView.findViewById(R.id.card_generated_playlist_play_id);

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playClickListenerRef.get().onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
