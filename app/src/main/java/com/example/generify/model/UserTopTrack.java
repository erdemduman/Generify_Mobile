package com.example.generify.model;

import android.widget.ImageView;

public class UserTopTrack{
    private String albumCover;
    private String albumId;
    private String artistId;
    private String trackId;
    private String artistName;
    private String albumName;
    private String trackName;

    public UserTopTrack(String albumCover, String albumId, String artistId, String trackId, String artistName, String albumName, String trackName) {
        this.albumCover = albumCover;
        this.albumId = albumId;
        this.artistId = artistId;
        this.trackId = trackId;
        this.artistName = artistName;
        this.albumName = albumName;
        this.trackName = trackName;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getSongId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongName() {
        return trackName;
    }
}
