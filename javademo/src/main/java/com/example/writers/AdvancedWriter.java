package com.example.writers;

import com.example.utils.ConsoleLogger;

import javax.inject.Inject;

public class AdvancedWriter implements Writer {

  private ConsoleLogger consoleLogger;
  private ConsoleLogger.Colors defaultColor;
  @Inject
  public AdvancedWriter(ConsoleLogger.Colors defaultColor, ConsoleLogger consoleLogger) {
    this.defaultColor = defaultColor;
    this.consoleLogger = consoleLogger;
  }

  @Override public void write(String message) {
    consoleLogger.write(defaultColor, "Advanced", message);
  }
}


