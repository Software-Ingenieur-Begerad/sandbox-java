package de.swingbe.postgres_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LctMsgCreateTable {

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

                //type: 0:echt,1:frei,2:wird benoetigt,3:virtuell
                //create a new table called friends and insert five rows into it
                st.addBatch("DROP TABLE IF EXISTS lct_msg_y");
                st.addBatch("CREATE TABLE lct_msg_y(" + "bs_id bigserial PRIMARY KEY NOT NULL" + ",vc_trip VARCHAR(20) NOT NULL" + ",vc_route VARCHAR(20)" + ",vc_tenant VARCHAR(20)" + ",vc_date VARCHAR(20) NOT NULL" + ",vc_time VARCHAR(20) NOT NULL" + ",vc_lat VARCHAR(20) NOT NULL" + ",vc_lon VARCHAR(20) NOT NULL" + ")");

                //method returns an array of committed changes
                int[] counts = st.executeBatch();

                con.commit();

                System.out.println("Committed " + counts.length + " updates");

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(LctMsgCreateTable.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(LctMsgCreateTable.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(LctMsgCreateTable.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}