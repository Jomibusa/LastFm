package com.example.lastfmapi;

import com.example.lastfmapi.Modelo.Artista;
import com.example.lastfmapi.Modelo.TopArtist;
import com.example.lastfmapi.Modelo.TopArtistResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LastFmService {

    String API_ROUTE ="?method=geo.gettopartists&country=colombia&api_key=829751643419a7128b7ada50de590067&format=json";

    @GET(API_ROUTE)
    Call<TopArtistResponse>getJson();

}
