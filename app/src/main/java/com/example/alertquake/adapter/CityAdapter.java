package com.example.alertquake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alertquake.R;
import com.example.alertquake.model.City;

import java.util.ArrayList;
import java.util.Locale;

public class CityAdapter extends ArrayAdapter<City> implements View.OnClickListener{

    private ArrayList<City> cities;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView cityName;
        TextView counter;
    }

    public CityAdapter(ArrayList<City> data, Context context) {
        super(context, R.layout.item_quake, data);
        this.cities = data;
        this.mContext=context;

    }

    @Nullable
    @Override
    public City getItem(int position) {
        return this.cities.get(position);
    }

    @Override
    public int getCount() {
        return this.cities.size();
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        City city=(City)object;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        City city = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_quake, parent, false);
        }

        TextView cityName = (TextView) convertView.findViewById(R.id.cityName);
        TextView counter = (TextView) convertView.findViewById(R.id.counter);

        cityName.setText(city != null ? city.getName() : "null");
        counter.setText(String.format(Locale.getDefault(), "%d", city.getCounter()));

        return convertView;
    }
}
