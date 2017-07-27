package com.rosiapps.daggerdemo.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.presentation.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener, MainNavigator {

    @Inject
    MainContract.Presenter presenter;
    @Inject
    Picasso picasso;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailNameTextView;
    private View progressBarView;
    private ImageView userImageView;

    @Override
    public void showProgress() {
        progressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarView.setVisibility(View.GONE);
    }

    @DebugLog
    public void renderUserInfo(final User info) {
        firstNameTextView.setText(info.firstName);
        lastNameTextView.setText(info.lastName);
        emailNameTextView.setText(info.email);
        picasso.load(info.photoUrl).into(userImageView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_more) {
            presenter.showMore();
        }
    }

    @Override
    public void gotoUsers() {
        startActivity(DetailsActivity.createIntent(this));
    }

    @Override
    public void goBack() {
        finish();
    }

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstNameTextView = (TextView) findViewById(R.id.txt_firstname);
        lastNameTextView = (TextView) findViewById(R.id.txt_lastname);
        emailNameTextView = (TextView) findViewById(R.id.txt_email);
        userImageView = (ImageView) findViewById(R.id.iv_user);
        progressBarView = findViewById(R.id.progressBar);
        findViewById(R.id.btn_more).setOnClickListener(this);
    }

    @DebugLog
    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @DebugLog
    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }
}
