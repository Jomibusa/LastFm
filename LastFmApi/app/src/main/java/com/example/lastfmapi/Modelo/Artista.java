package com.example.lastfmapi.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artista {

    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private int listeners;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("url")
    private String url;
    @SerializedName("streamable")
    private int stremeable;
    @SerializedName("images")
    private List<ImageItem> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStremeable() {
        return stremeable;
    }

    public void setStremeable(int stremeable) {
        this.stremeable = stremeable;
    }


    public List<ImageItem> getImages() {
        return images;
    }

    public void setImage(List<ImageItem> images) {
        this.images = images;
    }

    public String getImageUrl() {
        if (getImages() != null && getImages().size() > 0) {
            for (ImageItem img :
                    getImages()) {
                if (img.getSize().equalsIgnoreCase("large")) {
                    return img.getUrl();
                }
            }
        }
        return null;
    }
}
