package com.rosiapps.daggerdemo.presentation.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.presentation.details.details.UserDetailsFragment;
import com.rosiapps.daggerdemo.presentation.details.locations.LocationsFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class DetailsActivity extends AppCompatActivity implements ProgressViewInteractor, HasSupportFragmentInjector {

  private static final String EXTRA_USER_ID = "user_id";
  @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
  private SectionsPagerAdapter mSectionsPagerAdapter;
  private ViewPager mViewPager;
  private View progressBarView;

  public static final Intent createIntent(Context context, String userId) {
    Intent intent = new Intent(context, DetailsActivity.class);
    intent.putExtra(EXTRA_USER_ID, userId);
    return intent;
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentInjector;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_details, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void showProgress() {
    progressBarView.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBarView.setVisibility(View.GONE);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    mViewPager = (ViewPager) findViewById(R.id.container);
    mViewPager.setAdapter(mSectionsPagerAdapter);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(mViewPager);
    progressBarView = findViewById(R.id.progressBar);
  }

  private String getUserId() {
    return getIntent().getStringExtra(EXTRA_USER_ID);
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      if (position == 0) {
        return UserDetailsFragment.newInstance(getUserId());
      } else if (position == 1) {
        return LocationsFragment.newInstance(getUserId());
      } else {
        throw new RuntimeException("invalid page position " + position);
      }
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "User Details";
        case 1:
          return "Locations";
      }
      return null;
    }
  }
}
