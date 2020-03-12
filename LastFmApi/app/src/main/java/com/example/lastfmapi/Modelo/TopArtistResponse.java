package com.example.lastfmapi.Modelo;

import com.google.gson.annotations.SerializedName;

public class TopArtistResponse {

    @SerializedName("topartists")
    private TopArtist topArtists;

    public TopArtist getTopartists() {
        return topArtists;
    }

    public void setTopartists(TopArtist topArtists) {
        this.topArtists = topArtists;
    }

}
