package com.example.shaishavvalera.countries;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<CountryObject> countries = new ArrayList<>();
    private RecyclerView rv;
    private CountryAdapter countryAdapter;
    private ContentLoadingProgressBar  progressBar;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progressbar);
        new callData().execute();
        rv = (RecyclerView) findViewById(R.id.countrylist);
        countryAdapter = new CountryAdapter(countries,this);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutmanager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setAdapter(countryAdapter);
        sv = (SearchView)findViewById(R.id.searchbar);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText)
            {
                countryAdapter.getFilter().filter(newText);
                return true;
            }
        });
        if(haveNetworkConnection())
        {

        }
        else
        {
            openDialog();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.youtube:
                nextActivity(YoutubeActivity.class);
                return true;
            case R.id.quiz:
                nextActivity(QuizActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class callData extends FetchData
    {
        @Override
        protected void onPreExecute()
        {
            progressBar.show();
        }
        @Override
        protected void onPostExecute(ArrayList<CountryObject> result)
        {
            progressBar.hide();
            countries = result;
            countryAdapter.swapDataSet(countries);
        }
    }
    private void nextActivity(Class<?> next)
    {
        Intent i = new Intent(MainActivity.this,next);
        startActivity(i);
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    public void openDialog()
    {
        final AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.alert_demo,null);
        TextView text = (TextView) dialog.findViewById(R.id.textMessage);
        text.setText("Check Your Internet");
        alert.setPositiveButton("Okay!", new DialogInterface.OnClickListener()
        {
           @Override
            public void onClick(DialogInterface dialogInterface,int i)
           {
               MainActivity.this.finish();
           }
        });
        alert.setCancelable(false);
        alert.setView(dialog);
        alert.show();
    }

}
