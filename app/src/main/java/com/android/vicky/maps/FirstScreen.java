package com.android.vicky.maps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FirstScreen extends AppCompatActivity implements OnMapReadyCallback{
    String result = null;
public static final String MAPS = "maps_test";
    static final LatLng TutorialsPoint = new LatLng(21 , 57);

    private GoogleMap maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent in = new Intent(getApplicationContext(),SecondScreen.class);
                startActivity(in);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        maps = googleMap;
        LatLng kolkata = new LatLng(28.5491559,77.248399);
        maps.addMarker(new MarkerOptions().position(kolkata).title("Millinium Par").snippet("Sector-7"));
        float zoomLevel = 16;
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(kolkata,zoomLevel));

    }

}
