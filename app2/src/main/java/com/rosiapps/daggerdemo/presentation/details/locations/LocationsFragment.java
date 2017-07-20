package com.rosiapps.daggerdemo.presentation.details.locations;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.Location;
import com.rosiapps.daggerdemo.presentation.details.ProgressViewInteractor;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LocationsFragment extends Fragment implements LocationsContract.View {
  private static final String ARG_USER_ID = "user_id";
  @Inject LocationsContract.Presenter presenter;
  @Inject ProgressViewInteractor progressViewInteractor;
  private RecyclerView recycler;
  private LocationsAdapter adapter;

  public static LocationsFragment newInstance(String userId) {
    LocationsFragment fragment = new LocationsFragment();
    Bundle args = new Bundle();
    args.putString(ARG_USER_ID, userId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    AndroidSupportInjection.inject(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_locations, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recycler = (RecyclerView) view.findViewById(R.id.recycler);
    recycler.setHasFixedSize(true);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recycler.setLayoutManager(layoutManager);

    adapter = new LocationsAdapter();
    recycler.setAdapter(adapter);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.bindView(this);
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unbindView();
  }

  public String getUserId() {
    return getArguments().getString(ARG_USER_ID);
  }

  @Override public void showProgress() {
    progressViewInteractor.showProgress();
  }

  @Override public void hideProgress() {
    progressViewInteractor.hideProgress();
  }

  @Override public void renderLocations(List<Location> locations) {
    adapter.add(locations);
  }
}
