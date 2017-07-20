package com.example.writers;

import com.example.utils.ConsoleLogger;

import javax.inject.Inject;

public class AdvancedWriter implements Writer {

  private ConsoleLogger consoleLogger;

  @Inject
  public AdvancedWriter(ConsoleLogger consoleLogger) {
    this.consoleLogger = consoleLogger;
  }

  @Override public void write(String message) {
    consoleLogger.write(ConsoleLogger.Colors.Red, "Advanced", message);
  }
}


