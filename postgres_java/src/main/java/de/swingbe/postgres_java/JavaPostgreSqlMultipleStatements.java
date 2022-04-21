package de.swingbe.postgres_java;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlMultipleStatements {

    public static void main(String[] args) {

        //connection URL for the postgres database
        //jdbc:postgresql://<host>:<port>/<database name>
        String url = "jdbc:postgresql://localhost:5432/testdb";

        String user = "usr";
        String password = "#password";

        String query = "SELECT id, name FROM authors WHERE Id=1;" + "SELECT id, name FROM authors WHERE Id=2;" + "SELECT id, name FROM authors WHERE Id=3";

        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();

            if (isResult) {
                System.out.println("The method returns a boolean value indicating if the first result is a ResultSet object");
            }
            do {
                try (ResultSet rs = pst.getResultSet()) {

                    while (rs.next()) {

                        System.out.print(rs.getInt(1));
                        System.out.print(": ");
                        System.out.println(rs.getString(2));
                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlMultipleStatements.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
