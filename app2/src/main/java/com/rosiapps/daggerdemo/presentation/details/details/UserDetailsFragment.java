package com.rosiapps.daggerdemo.presentation.details.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.UserDetails;
import com.rosiapps.daggerdemo.presentation.details.ProgressViewInteractor;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UserDetailsFragment extends Fragment implements UserDetailsContract.View {
  private static final String ARG_USER_ID = "user_id";
  @Inject UserDetailsContract.Presenter presenter;
  @Inject ProgressViewInteractor progressViewInteractor;

  private TextView phoneTextView;
  private TextView addressTextView;
  private TextView hobbiesTextView;

  public UserDetailsFragment() {
  }

  public static UserDetailsFragment newInstance(String userId) {
    UserDetailsFragment fragment = new UserDetailsFragment();
    Bundle args = new Bundle();
    args.putString(ARG_USER_ID, userId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_user_details, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    phoneTextView = (TextView) view.findViewById(R.id.txt_phone);
    addressTextView = (TextView) view.findViewById(R.id.txt_address);
    hobbiesTextView = (TextView) view.findViewById(R.id.txt_hobbies);
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

  @Override public void renderUserDetails(UserDetails details) {
    phoneTextView.setText(details.phone);
    addressTextView.setText(details.address);
    hobbiesTextView.setText(details.hobbies);
  }
}
