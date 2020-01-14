package com.example.generify.model;

public class GeneratePlaylist {
    private String albumCover;
    private String trackUri;
    private String artistName;
    private String trackName;

    public GeneratePlaylist(String albumCover, String trackUri, String artistName, String trackName) {
        this.albumCover = albumCover;
        this.trackUri = trackUri;
        this.artistName = artistName;
        this.trackName = trackName;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getTrackUri() {
        return trackUri;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }
}
