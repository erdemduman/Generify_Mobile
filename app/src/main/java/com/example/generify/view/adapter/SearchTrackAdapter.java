package com.example.generify.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generify.R;
import com.example.generify.model.SearchTrack;
import com.example.generify.util.RecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchTrackAdapter extends RecyclerView.Adapter<SearchTrackAdapter.ViewHolder> {
    private List<SearchTrack> searchTrackList;
    private RecyclerViewItemClickListener listener;

    public SearchTrackAdapter(List<SearchTrack> searchTracks, RecyclerViewItemClickListener listener){
        this.searchTrackList = searchTracks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchTrackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_track, parent, false);
        final SearchTrackAdapter.ViewHolder viewHolder = new SearchTrackAdapter.ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getLayoutPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTrackAdapter.ViewHolder holder, int position) {
        Picasso.get().load(searchTrackList.get(position).getAlbumCover()).into(holder.albumCover);
        holder.artistName.setText(searchTrackList.get(position).getArtistName());
        holder.trackName.setText(searchTrackList.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return searchTrackList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView albumCover;
        public TextView trackName;
        public TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.card_search_track_album_cover_id);
            trackName = itemView.findViewById(R.id.card_search_track_track_name_id);
            artistName = itemView.findViewById(R.id.card_search_track_artist_id);
        }
    }
}
