package de.swingbe.postgres_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlProperties {

    public static Properties readProperties() {

        Properties props = new Properties();
        Path path = Paths.get("src/main/resources/database.properties");

        try {
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);

            //load properties into object
            props.load(br);

        } catch (IOException e) {
            Logger.getLogger(JavaPostgreSqlProperties.class.getName()).log(
                    Level.SEVERE, null, e);
        }
        return props;
    }

    public static void main(String[] args) {

        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        //get all columns from a table
        String query = "SELECT * FROM authors";

        //create prepared statement using placeholders instead of directly writing values
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            //advance cursor to the next record
            //return false if there are no more records in the result set
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" | ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlProperties.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
