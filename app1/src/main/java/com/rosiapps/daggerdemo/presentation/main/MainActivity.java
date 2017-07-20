package com.rosiapps.daggerdemo.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosiapps.daggerdemo.AppComponent;
import com.rosiapps.daggerdemo.HasComponent;
import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.User;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity implements MainContract.View {

  @Inject MainContract.Presenter presenter;
  @Inject Picasso picasso;
  private TextView firstNameTextView;
  private TextView lastNameTextView;
  private TextView emailNameTextView;
  private View progressBarView;
  private ImageView userImageView;

  @Override public void showProgress() {
    progressBarView.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBarView.setVisibility(View.GONE);
  }

  @DebugLog
  public void renderUserInfo(final User info) {
    firstNameTextView.setText(info.firstName);
    lastNameTextView.setText(info.lastName);
    emailNameTextView.setText(info.email);
    picasso.load(info.photoUrl).fit().into(userImageView);
  }

  @DebugLog
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    firstNameTextView = (TextView) findViewById(R.id.txt_firstname);
    lastNameTextView = (TextView) findViewById(R.id.txt_lastname);
    emailNameTextView = (TextView) findViewById(R.id.txt_email);
    userImageView = (ImageView)findViewById(R.id.iv_user);
    progressBarView = findViewById(R.id.progressBar);

    injectDependencies();
  }

  @DebugLog
  @Override protected void onPause() {
    super.onPause();
    presenter.unbindView();
  }

  @DebugLog
  @Override protected void onResume() {
    super.onResume();
    presenter.bindView(this);
  }

  private void injectDependencies() {
    HasComponent<AppComponent> holder = (HasComponent<AppComponent>) getApplication();
    AppComponent appComponent = holder.getComponent();
    DaggerMainComponent.builder()
        .appComponent(appComponent)
        .mainModule(new MainModule(this))
        .build()
        .inject(this);
  }
}
