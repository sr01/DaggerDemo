package com.example;

import com.example.utils.ConsoleLogger;
import com.example.writers.AdvancedWriter;
import com.example.writers.Writer;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  @Provides
  public ConsoleLogger consoleLogger() {
    return new ConsoleLogger();
  }

  @Provides
  public Writer provideWriter(ConsoleLogger consoleLogger) {
    return new AdvancedWriter(consoleLogger);
  }

//  @Provides
//  public Writer provideWriter(){
//    return new SimpleWriter();
//  }
}
