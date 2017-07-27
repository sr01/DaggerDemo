package com.example.field;

import com.example.writers.Writer;

import javax.inject.Inject;

public class DIApplication
{
    @Inject
    Writer writer;

    public DIApplication()
    {
        AppComponent component = DaggerAppComponent
                .builder()
                .build();

        component.inject(this);
    }

    public static void main(String[] args)
    {
        DIApplication app = new DIApplication();
        app.start();
    }

    public void start()
    {
        writer.write("Hello dagger, this is field injection demo :)");
    }
}
