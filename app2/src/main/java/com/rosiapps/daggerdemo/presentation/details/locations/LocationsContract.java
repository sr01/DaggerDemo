package com.rosiapps.daggerdemo.presentation.details.locations;

import com.rosiapps.daggerdemo.data.Location;

import java.util.List;

public interface LocationsContract {
  interface View {
    void showProgress();

    void hideProgress();

    void renderLocations(List<Location> locations);
  }

  interface Presenter {
    void bindView(View view);

    void unbindView();
  }
}
