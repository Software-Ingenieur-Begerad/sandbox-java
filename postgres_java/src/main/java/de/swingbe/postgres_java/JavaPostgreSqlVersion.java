package de.swingbe.postgres_java;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlVersion {

    public static void main(String[] args) {

        //connection URL
        String url = "jdbc:postgresql://192.168.178.25:5432/testdb";
        String user = "user";
        String key = "key";

        try (
                //establish connection
                Connection con = DriverManager.getConnection(url, user, key);
                //create object for sending SQL statements
                Statement st = con.createStatement();
                //execute SQL statement
                //rs is a table of data returned by a SQL statement
                ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
