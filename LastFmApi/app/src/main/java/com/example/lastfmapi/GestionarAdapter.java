package com.example.lastfmapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GestionarAdapter extends BaseAdapter {

    String[] resultado;
    String[] imgId;
    String[] link;
    Context contexto;
    private static LayoutInflater inflater= null;
    private WebView web;


    public GestionarAdapter (MainActivity mainActivity,String[]progNombreLista,String[]progImg, String[]linkurl) {
        resultado = progNombreLista;
        contexto = mainActivity;
        imgId = progImg;
        link = linkurl;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return resultado.length;
    }
    @Override
    public  Object getItem(int posicion) {
        return posicion;
    }
    @Override
    public  long  getItemId(int posicion) {
        return posicion;
    }
    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int posicion, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View fila;
        View web;

        fila = inflater.inflate(R.layout.lista_artistas, null);

        holder.tv=(TextView) fila.findViewById(R.id.textView);
        holder.img=(ImageView) fila.findViewById(R.id.imgArtist);

        holder.tv.setText(resultado[posicion]);
        Picasso.get().load(imgId[posicion]).into(holder.img);


        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(contexto, "Seleccionaste "+resultado[posicion],
                        Toast.LENGTH_LONG).show();

                Uri uriUrl = Uri.parse(link[posicion]);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                contexto.startActivity(launchBrowser);

            }
        });
        return fila;
    }
}
