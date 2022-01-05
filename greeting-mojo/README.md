# Overview
This project shows how to create a Maven plugin.

# Preparation
Copy settings into user folder like this.

```
cp settings.xml ~/.m2/
```

# Execution
Call the following instructions to execute the plugin on the command line.

```
mvn clean install
mvn hello:sayhi
```

# Links
[Example](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html#Plugin_Naming_Convention_and_Apache_Maven_Trademark)
