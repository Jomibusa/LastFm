package com.example.lastfmapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.os.Bundle;


import android.widget.ListView;
import android.widget.TextView;


import com.example.lastfmapi.Modelo.Artista;
import com.example.lastfmapi.Modelo.TopArtistResponse;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textoPrueba;
    private ListView listview;
    private String[] ArrayArtistas;
    private static List<String> arrayListArtist = new ArrayList<String>();
    private static List<String> arrayListImagenes = new ArrayList<String>();
    private static List<String> arrayListUrls = new ArrayList<String>();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getArtistas();


    }

    public void getListView(List<String> lista, List<String> imagenes, List<String> urls) {
        context = this;

        String[] stringArray = lista.toArray(new String[0]);
        String[] stringImagenes = imagenes.toArray(new String[0]);
        String[] strinUrls = urls.toArray(new String[0]);
        listview = (ListView) findViewById(R.id.lvListado);
        listview.setAdapter(new GestionarAdapter(this, stringArray,stringImagenes, strinUrls));

    }


    public void getArtistas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LastFmService lastfm = retrofit.create(LastFmService.class);

        Call<TopArtistResponse> call = lastfm.getJson();

        call.enqueue(new Callback<TopArtistResponse>() {
            @Override
            public void onResponse(Call<TopArtistResponse> call, Response<TopArtistResponse> response) {


                TopArtistResponse contenido = response.body();

                List<Artista> listArtist = contenido.getTopartists().getArtists();

                for (Artista artista : listArtist) {
                    String content = "\n";
                    String imagenes = "";
                    String url = "";
                    content += "Name ► " + artista.getName() + "\n";
                    content += "Listeners ► " + artista.getListeners() + "\n";
                    content += "MBID ► " + artista.getMbid() + "\n";
                    url += artista.getUrl();
                    content += "Stremeable ► " + artista.getStremeable() + "\n";
                    imagenes += artista.getImageUrl();
                    arrayListArtist.add(content);
                    arrayListImagenes.add(imagenes);
                    arrayListUrls.add(url);
                }
                getListView(arrayListArtist,arrayListImagenes,arrayListUrls);
            }

            @Override
            public void onFailure(Call<TopArtistResponse> call, Throwable t) {
                textoPrueba.setText(t.getMessage());
            }
        });

    }
}
