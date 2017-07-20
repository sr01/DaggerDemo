package com.example.writers;

public class SimpleWriter implements Writer {
  @Override public void write(String message) {
    System.out.println("[Simple] " + message);
  }
}
