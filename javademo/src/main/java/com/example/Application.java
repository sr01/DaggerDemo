package com.example;

import com.example.writers.Writer;

import javax.inject.Inject;

public class Application {

  private Writer writer;

  @Inject
  public Application(Writer writer) {
    this.writer = writer;
  }

  public static void main(String[] args) {

    Application app = DaggerAppComponent
        .builder()
        .build()
        .app();

    app.start();
  }

  public void start() {
    writer.write("Hello dagger :)");
  }
}
