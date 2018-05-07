package com.example.shaishavvalera.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Shaishav Valera on 06-Nov-17.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private Double latitude;
    private Double longitude;
    private String countryname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_fragment);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i = getIntent();
        latitude = i.getDoubleExtra("latitude",0.0);
        longitude = i.getDoubleExtra("longitude",0.0);
        countryname = i.getStringExtra("name");
    }
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        LatLng countrylocation = new LatLng(latitude,longitude);
        googleMap.addMarker(new MarkerOptions().position(countrylocation).title(countryname));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(countrylocation,3.5f));
    }
}
