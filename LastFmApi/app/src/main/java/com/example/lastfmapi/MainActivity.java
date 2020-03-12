package com.example.lastfmapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastfmapi.Modelo.Artista;
import com.example.lastfmapi.Modelo.TopArtist;
import com.example.lastfmapi.Modelo.TopArtistResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textoPrueba;

    ListView list;
    ArrayList<String> name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoPrueba = findViewById(R.id.texto);
        getArtistas();


    }

    public void getArtistas(){
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

                //textoPrueba.setText(contenido.getTopartists().getArtists().toString());

                List<Artista> listArtist = contenido.getTopartists().getArtists();

                for(Artista artista:listArtist){
                    String content = "Artist:\n";
                    content += "name:"+ artista.getName() + "\n";
                    content += "listeners:"+ artista.getListeners() + "\n";
                    content += "mbid:"+ artista.getMbid() + "\n";
                    content += "url:"+ artista.getUrl() + "\n";
                    content += "stremeable:"+ artista.getStremeable()+ "\n";
                    content += "image:" + artista.getImageUrl() + "\n\n";
                    textoPrueba.append(content);
                }
            }

            @Override
            public void onFailure(Call<TopArtistResponse> call, Throwable t) {
                textoPrueba.setText(t.getMessage());
            }
        });
    }
}
