package com.example.shaishavvalera.countries;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shaishav Valera on 24-Oct-17.
 */
class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView tv;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView)
    {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.country_name);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener (ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
public class CountryAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements Filterable
{
    private ArrayList<CountryObject> countryList;
    private ArrayList<CountryObject> countrycopy;
    private Context context;
    public CountryAdapter(ArrayList<CountryObject> co,Context context)
    {
        this.countryList = co;
        this.context = context;
    }
    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();
                if(charString.isEmpty())
                {
                    countryList = countrycopy;
                }
                else
                {
                    ArrayList<CountryObject> filteredlist = new ArrayList<>();
                    for(CountryObject c:countrycopy)
                    {
                        if(c.getName().toLowerCase().contains(charString))
                        {
                            filteredlist.add(c);
                        }
                    }
                    countryList = filteredlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = countryList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                countryList = (ArrayList<CountryObject>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public void swapDataSet(ArrayList<CountryObject> newData)
    {
        this.countryList = newData;
        this.countrycopy = newData;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        final CountryObject countryObject = countryList.get(position);
        holder.tv.setText(countryObject.getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position)
            {
                Intent i = new Intent(context,CountryInfo.class);
                final CountryObject co = countryList.get(position);
                i.putExtra("name",co.getName());
                i.putExtra("capital",co.getCapital());
                i.putExtra("continent",co.getContinent());
                i.putExtra("currency",co.getCurrency());
                i.putExtra("area",co.getArea());
                i.putExtra("population",co.getPopulation());
                i.putExtra("flagcode",co.getFlagcode());
                i.putExtra("latitude",co.getLatitude());
                i.putExtra("longitude",co.getLongitude());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(countryList==null)
        {
            return 0;
        }
        return countryList.size();
    }
}