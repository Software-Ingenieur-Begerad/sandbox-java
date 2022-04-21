package de.swingbe.postgres_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlBatchUpdates {

    public static void main(String[] args) {

        //connection URL for the postgres database
        //jdbc:postgresql://<host>:<port>/<database name>
        String url = "jdbc:postgresql://localhost:5432/testdb";

        String user = "usr";
        String password = "#password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            try (Statement st = con.createStatement()) {

                //autocommit should always be turned off when doing batch updates
                con.setAutoCommit(false);

                //create a new table called friends and insert five rows into it
                st.addBatch("DROP TABLE IF EXISTS friends");
                st.addBatch("CREATE TABLE friends(id serial, name VARCHAR(10))");
                st.addBatch("INSERT INTO friends(name) VALUES ('Jane')");
                st.addBatch("INSERT INTO friends(name) VALUES ('Tom')");
                st.addBatch("INSERT INTO friends(name) VALUES ('Rebecca')");
                st.addBatch("INSERT INTO friends(name) VALUES ('Jim')");
                st.addBatch("INSERT INTO friends(name) VALUES ('Robert')");

                //method returns an array of committed changes
                int[] counts = st.executeBatch();

                con.commit();

                System.out.println("Committed " + counts.length + " updates");

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}