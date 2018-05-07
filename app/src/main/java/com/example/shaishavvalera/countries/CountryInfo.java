package com.example.shaishavvalera.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shaishav Valera on 25-Oct-17.
 */

public class CountryInfo extends AppCompatActivity
{
    Double lat,lon;
    ImageView flag;
    TextView name,capital,continent,area,currency,population,flagcode;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_info);
        Intent i = getIntent();
        flag = (ImageView) findViewById(R.id.flag);
        name = (TextView) findViewById(R.id.countryname);
        capital = (TextView) findViewById(R.id.capital);
        continent = (TextView) findViewById(R.id.continent);
        area = (TextView) findViewById(R.id.area);
        currency = (TextView) findViewById(R.id.currency);
        population = (TextView) findViewById(R.id.population);
        flagcode = (TextView) findViewById(R.id.flagcode);
        name.setText(i.getStringExtra("name"));
        capital.setText(i.getStringExtra("capital"));
        continent.setText(i.getStringExtra("continent"));
        area.setText(""+i.getDoubleExtra("area",0.0));
        currency.setText(i.getStringExtra("currency"));
        population.setText(""+i.getDoubleExtra("population",0.0));
        flagcode.setText(i.getStringExtra("flagcode"));
        lat = i.getDoubleExtra("latitude",0.0);
        lon = i.getDoubleExtra("longitude",0.0);
        int resid = getResources().getIdentifier(flagcode.getText().toString().toLowerCase(),"drawable",getPackageName());
        flag.setImageResource(resid);
    }
    public void gotoMap(View v)
    {
        Intent i = new Intent(CountryInfo.this,MapsActivity.class);
        i.putExtra("latitude",lat);
        i.putExtra("longitude",lon);
        i.putExtra("name",name.getText().toString());
        startActivity(i);
    }
}
