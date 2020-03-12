package com.example.lastfmapi.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArtist {

    //@SerializedName("topartists")
    //private String tArtist;
    @SerializedName("artist")
    private List<Artista> artists;

    public List<Artista> getArtists() {
        return artists;
    }

    public void setArtists(List<Artista> artists) {
        this.artists = artists;
    }

}
