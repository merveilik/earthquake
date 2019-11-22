package com.example.alertquake.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alertquake.R;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.HorizontalViewHolder> {

    private int[] items;

    public GuideAdapter(int[] items) {
        this.items = items;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_guide, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        holder.image.setImageResource(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public HorizontalViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.guide_image);
        } }
}