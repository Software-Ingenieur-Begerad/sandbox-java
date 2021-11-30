package de.swingbe.tailer;

import org.apache.commons.io.input.TailerListenerAdapter;

public class MyListener extends TailerListenerAdapter {
    @Override
    public void handle(String line) {
        System.out.println(line);
    }
}
