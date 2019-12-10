package com.example.generify.util.parser;

import com.example.generify.model.SearchTrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchParser {
    public static List<SearchTrack> searchTrackParser(String response){
        JSONObject jsonObject;
        List<SearchTrack> searchTrackList = new ArrayList<>();
        try{
            jsonObject = new JSONObject(response);
            JSONArray tracks = jsonObject.getJSONObject("tracks").getJSONArray("items");

            for(int i = 0; i < tracks.length(); i++){

                JSONObject track = tracks.getJSONObject(i);

                JSONObject album = tracks
                        .getJSONObject(i)
                        .getJSONObject("album");

                JSONObject artist = tracks
                        .getJSONObject(i)
                        .getJSONArray("artists")
                        .getJSONObject(0);

                String trackId = track.getString("id");
                String trackName = track.getString("name");

                String albumId = album.getString("id");
                String albumName = album.getString("name");

                String albumCover = album.getJSONArray("images").getJSONObject(0).getString("url");

                String artistId = artist.getString("id");
                String artistName = artist.getString("name");

                searchTrackList.add(new SearchTrack(
                        albumCover,
                        artistId,
                        albumId,
                        trackId,
                        artistName,
                        albumName,
                        trackName));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return searchTrackList;
    }
}
