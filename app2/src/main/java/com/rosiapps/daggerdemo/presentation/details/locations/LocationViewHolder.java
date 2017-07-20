package com.rosiapps.daggerdemo.presentation.details.locations;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.Location;

class LocationViewHolder extends RecyclerView.ViewHolder {

  private TextView locationTextView;

  public LocationViewHolder(View itemView) {
    super(itemView);
    locationTextView = (TextView) itemView.findViewById(R.id.txt_location);
  }

  public void bind(Location location) {
    locationTextView.setText(location.toString());
  }
}
