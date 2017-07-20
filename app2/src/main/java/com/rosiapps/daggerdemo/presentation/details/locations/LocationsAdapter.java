package com.rosiapps.daggerdemo.presentation.details.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.Location;

import java.util.ArrayList;
import java.util.List;

class LocationsAdapter extends RecyclerView.Adapter<LocationViewHolder> {

  private List<Location> locations;

  public LocationsAdapter() {
    locations = new ArrayList<>(10);
  }

  @Override public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
    return new LocationViewHolder(view);
  }

  @Override public void onBindViewHolder(LocationViewHolder holder, int position) {
    Location location = locations.get(position);
    holder.bind(location);
  }

  @Override public int getItemCount() {
    return locations.size();
  }

  public void add(Location location) {
    locations.add(location);
    notifyItemInserted(locations.size() - 1);
  }

  public void add(List<Location> locations) {
    this.locations.addAll(locations);
    notifyDataSetChanged();
  }
}
