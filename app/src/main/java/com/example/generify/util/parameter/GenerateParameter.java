package com.example.generify.util.parameter;

public class GenerateParameter {
    private String trackId;
    private String feature;

    public GenerateParameter(String trackId, String feature){
        this.trackId = trackId;
        this.feature = feature;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getFeature() {
        return feature;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
