package com.example.generify.util.parser;

import com.example.generify.model.GeneratePlaylist;
import com.example.generify.model.SearchTrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenerateParser {
    public static List<GeneratePlaylist> generatePlaylistParser(String response){
        JSONObject jsonObject;
        List<GeneratePlaylist> generatePlaylistList = new ArrayList<>();
        try{
            jsonObject = new JSONObject(response);
            JSONArray tracks = jsonObject.getJSONArray("generify");

            for(int i = 0; i < tracks.length(); i++){

                JSONObject track = tracks.getJSONObject(i);

                String artistName = track.getString("artist");
                String trackName = track.getString("track");
                String trackUri = track.getString("track_uri");
                String albumCover = track.getString("album_cover");


                generatePlaylistList.add(new GeneratePlaylist(
                        albumCover,
                        trackUri,
                        artistName,
                        trackName));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return generatePlaylistList;
    }
}
