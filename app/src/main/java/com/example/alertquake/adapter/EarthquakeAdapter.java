package com.example.alertquake.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alertquake.R;
import com.example.alertquake.model.Earthquake;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.ViewHolder> {

    private Earthquake[] mDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMagnitude;
        public TextView tvQuakeLocation;
        public TextView tvQuakeTime;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMagnitude = itemView.findViewById(R.id.magnitude);
            tvQuakeLocation = itemView.findViewById(R.id.earthquakeLocation);
            tvQuakeTime = itemView.findViewById(R.id.earthquakeTime);
        }

        public void setBackgroundColor(double magnitude) {
            if(magnitude > 5) {
                itemView.setBackgroundColor(Color.rgb(237,100,9));
            }
            else if (magnitude <= 5) {
                itemView.setBackgroundColor(Color.rgb(183, 237, 90));
            }
            else {
                itemView.setBackgroundColor(Color.rgb(255, 255, 0));
            }
        }
    }

    public EarthquakeAdapter(Context context, Earthquake[] myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public EarthquakeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_quake, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Earthquake earthquake = mDataset[position];

        holder.tvMagnitude.setText(String.valueOf(earthquake.getMagnitude()));
        holder.tvQuakeLocation.setText(earthquake.getLocation());
        holder.tvQuakeTime.setText(earthquake.getTime());

        double magnitude = earthquake.getMagnitude();
        holder.setBackgroundColor(magnitude);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}