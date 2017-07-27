package com.example.no_di;

import com.example.writers.SimpleWriter;
import com.example.writers.Writer;

public class NoDIApplication
{

    private Writer writer;

    public NoDIApplication()
    {
        this.writer = new SimpleWriter();
    }

    public static void main(String[] args)
    {

        NoDIApplication app = new NoDIApplication();
        app.start();
    }

    public void start()
    {
        writer.write("This, is simple demo of simple code which constructs its dependencies directly.");
    }
}
