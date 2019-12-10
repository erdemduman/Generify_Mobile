package com.example.generify.model;

public class SearchTrack {
    private String albumCover;
    private String artistId;
    private String albumId;
    private String trackId;
    private String artistName;
    private String albumName;
    private String trackName;

    public SearchTrack(String albumCover, String artistId, String albumId, String trackId, String artistName, String albumName, String trackName) {
        this.albumCover = albumCover;
        this.artistId = artistId;
        this.albumId = albumId;
        this.trackId = trackId;
        this.artistName = artistName;
        this.albumName = albumName;
        this.trackName = trackName;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getTrackName() {
        return trackName;
    }
}
