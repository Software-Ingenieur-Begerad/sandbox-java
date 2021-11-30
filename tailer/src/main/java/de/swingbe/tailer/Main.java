package de.swingbe.tailer;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        TailerListener listener = new MyListener();
        Tailer tailer = new Tailer(new File("NetPeerManager.log"), listener, 500);
        tailer.run();

        return;
    }
}
