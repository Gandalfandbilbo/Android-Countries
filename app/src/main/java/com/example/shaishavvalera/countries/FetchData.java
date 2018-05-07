package com.example.shaishavvalera.countries;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Shaishav Valera on 24-Oct-17.
 */

public class FetchData extends AsyncTask<Void,Void,ArrayList<CountryObject>>
{
    private ArrayList<CountryObject> co = new ArrayList<>();
    protected ArrayList<CountryObject> doInBackground(Void... urls)
    {
        String url = "https://restcountries.eu/rest/v2/all";
        try
        {
            URL link = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
                br.close();
                JSONArray all = new JSONArray(sb.toString());
                for (int i = 0; i < all.length(); i++)
                {
                    JSONObject obj = new JSONObject(all.getString(i));
                    if(obj.getString("name").equalsIgnoreCase("United States Minor Outlying Islands")||obj.getString("area").equalsIgnoreCase("null"))
                    {
                        continue;
                    }
                    String name = obj.getString("name");
                    String capital = obj.getString("capital");
                    String continent = obj.getString("region");
                    JSONArray latlng = obj.getJSONArray("latlng");
                    Double latitude = latlng.getDouble(0);
                    Double longitude = latlng.getDouble(1);
                    Double area = obj.getDouble("area");
                    JSONArray currencies = obj.getJSONArray("currencies");
                    JSONObject c = currencies.getJSONObject(0);
                    String currency = c.getString("name");
                    Double population = obj.getDouble("population");
                    String flagcode = obj.getString("alpha2Code");
                    CountryObject currentCountry = new CountryObject(name,capital,continent,latitude,longitude,area,currency,population,flagcode);
                    co.add(currentCountry);
                }
                return co;
            }
            finally
            {
                urlConnection.disconnect();
            }
        }
        catch (Exception e)
        {
            Log.e("Error:",""+e);
            return null;
        }

    }
}
