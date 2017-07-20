package com.rosiapps.daggerdemo.data;

public class User {
  public String firstName;
  public String lastName;
  public String email;
  public String photoUrl;

  public User() {
  }

  public User(String firstName, String lastName, String email, String photoUrl) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.photoUrl = photoUrl;
  }
}
