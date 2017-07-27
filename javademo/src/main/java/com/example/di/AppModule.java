package com.example.di;

import com.example.utils.ConsoleLogger;
import com.example.writers.AdvancedWriter;
import com.example.writers.Writer;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  @Provides
  public Writer provideWriter(ConsoleLogger consoleLogger) {
    return new AdvancedWriter(ConsoleLogger.Colors.Green, consoleLogger);
  }

  @Provides
  public ConsoleLogger consoleLogger() {
    return new ConsoleLogger();
  }
}
