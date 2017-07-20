package com.rosiapps.daggerdemo.data;

public class User {
  public String id;
  public String firstName;
  public String lastName;
  public String email;
  public String photoUrl;

  public User() {
  }

  public User(String id, String firstName, String lastName, String email, String photoUrl) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.photoUrl = photoUrl;
  }

  @Override public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", photoUrl='" + photoUrl + '\'' +
        '}';
  }
}
