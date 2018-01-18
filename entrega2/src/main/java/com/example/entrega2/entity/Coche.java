package com.example.entrega2.entity;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sergio on 2/12/17.
 */

@IgnoreExtraProperties
public class Coche {
    public int Fabricado;
    public String marca;
    public String modelo;
    public String urlImg;
    public double lat;
    public double lon;
    // Fuera de Firebase
    private Marker marker=null;

    public Coche() {
    }

    public Coche(int Fabricado, String marca, String modelo, String urlImg, double lat, double lon) {
        this.Fabricado = Fabricado;
        this.marca = marca;
        this.modelo = modelo;
        this.urlImg = urlImg;
        this.lat = lat;
        this.lon = lon;
    }

    public void setMarker(Marker marker){
        this.marker = marker;
    }

    public Marker getMarker(){
        return marker;
    }
}
