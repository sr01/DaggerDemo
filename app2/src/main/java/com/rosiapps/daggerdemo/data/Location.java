package com.rosiapps.daggerdemo.data;

import java.util.Date;

public class Location {
  public double longitude;
  public double latitude;
  public Date timestamp;

  public Location(double latitude, double longitude, long timestamp) {
    this(latitude, longitude, new Date(timestamp));
  }

  public Location(double latitude, double longitude, Date timestamp) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.timestamp = timestamp;
  }

  @Override public String toString() {
    return longitude + ", " + latitude + ", " + timestamp;
  }
}
