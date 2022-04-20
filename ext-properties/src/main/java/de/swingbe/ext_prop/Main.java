package de.swingbe.ext_prop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("version.properties");

        try {
            properties.load(url.openStream());
        } catch (FileNotFoundException fie) {
            fie.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("version: " + properties.getProperty("version"));
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + " - " + properties.getProperty(key));
        }
    }


}
