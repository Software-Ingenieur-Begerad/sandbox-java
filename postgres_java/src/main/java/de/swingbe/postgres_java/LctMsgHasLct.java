package de.swingbe.postgres_java;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LctMsgHasLct {

    public static void main(String[] args) {

        //connection URL for the postgres database
        //jdbc:postgresql://<host>:<port>/<database name>
        String url = "jdbc:postgresql://localhost:5432/testdb";

        String user = "usr";
        String password = "#password";

        //get all columns from a table
        String date = "2022-05-10";
        String trip = "4457006";
        String query = "SELECT " + "CASE WHEN EXISTS " + "(" + "SELECT * from lct_msg_y where vc_date='" + date + "' AND vc_trip='" + trip + "')" + "THEN 'true'" + "ELSE 'false'" + "END;";

        //create prepared statement using placeholders instead of directly writing values
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            //advance cursor to the next record
            //return false if there are no more records in the result set
            while (rs.next()) {
                System.out.println("" + rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(LctMsgHasLct.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
