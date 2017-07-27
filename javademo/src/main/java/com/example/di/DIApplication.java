package com.example.di;

import com.example.writers.Writer;

import javax.inject.Inject;

public class DIApplication
{
  private Writer writer;

  @Inject
  public DIApplication(Writer writer) {
    this.writer = writer;
  }

  public void start() {
    writer.write("Hello dagger, this is constructor injection demo:)");
  }

  public static void main(String[] args) {

    DIApplication app = DaggerAppComponent
            .builder()
            .build()
            .app();

    app.start();
  }
}
