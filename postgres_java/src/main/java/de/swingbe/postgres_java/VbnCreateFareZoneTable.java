package de.swingbe.postgres_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VbnCreateFareZoneTable {

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
                st.addBatch("DROP TABLE IF EXISTS fare_zone");
                st.addBatch("CREATE TABLE fare_zone(ss_id smallserial PRIMARY KEY NOT NULL"
                        + ",si_number smallint NOT NULL"
                        + ",si_number_internal smallint"
                        + ",vc_name VARCHAR(255)"
                        + ",vc_name_short VARCHAR(255)"
                        + ",vc_type VARCHAR(15) NOT NULL)");
                st.addBatch("INSERT INTO fare_zone("
                        + "si_number"
                        + ",si_number_internal"
                        + ",vc_name"
                        + ",vc_name_short"
                        + ",vc_type) VALUES (" + "100,1000,'Bremen','Bremen','echt')");

                //method returns an array of committed changes
                int[] counts = st.executeBatch();

                con.commit();

                System.out.println("Committed " + counts.length + " updates");

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(VbnCreateFareZoneTable.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(VbnCreateFareZoneTable.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(VbnCreateFareZoneTable.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}