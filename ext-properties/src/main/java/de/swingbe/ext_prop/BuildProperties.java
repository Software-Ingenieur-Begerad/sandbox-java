package de.swingbe.ext_prop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BuildProperties {
    private final Properties properties;

    private final java.net.URL url;

    public BuildProperties() {
        url = ClassLoader.getSystemResource("build.properties");
        properties = new Properties();
    }

    public String getVersion() {

        try {
            properties.load(url.openStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty("build.version");
    }

    public String getDate() {

        try {
            properties.load(url.openStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty("build.date");
    }
}
