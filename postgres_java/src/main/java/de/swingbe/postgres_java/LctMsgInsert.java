package de.swingbe.postgres_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LctMsgInsert {

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
                st.addBatch("INSERT INTO lct_msg_y(" + "vc_trip" + ",vc_date" + ",vc_time" + ",vc_lat" + ",vc_lon" + ") VALUES (" + "'4457006'," + "'2022-05-10'," + "'13:34:05,824'," + "'74635669'," + "'534651826')");

                //method returns an array of committed changes
                int[] counts = st.executeBatch();

                con.commit();

                System.out.println("Committed " + counts.length + " updates");

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(LctMsgInsert.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(LctMsgInsert.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(LctMsgInsert.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}