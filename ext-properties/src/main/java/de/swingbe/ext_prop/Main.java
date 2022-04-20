package de.swingbe.ext_prop;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        VersionProperties versionProperties = new VersionProperties();
        System.out.println("version: " + versionProperties.getVersion());
        BuildProperties buildProperties = new BuildProperties();
        System.out.println("build.version: " + buildProperties.getVersion());
        System.out.println("build.date: " + buildProperties.getDate());
        System.out.println("Done!");
    }


}
