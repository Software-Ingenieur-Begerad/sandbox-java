package de.swingbe.postgres_java;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlCopyToTextFile {

    public static void main(String[] args) {

        //connection URL for the postgres database
        //jdbc:postgresql://<host>:<port>/<database name>
        String url = "jdbc:postgresql://localhost:5432/testdb";

        String user = "usr";
        String password = "#password";

        try {

            Connection con = DriverManager.getConnection(url, user, password);
            CopyManager cm = new CopyManager((BaseConnection) con);

            String fileName = "src/main/resources/friends.txt";

            try (FileOutputStream fos = new FileOutputStream(fileName); OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

                cm.copyOut("COPY friends TO STDOUT WITH DELIMITER AS ','", osw);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlCopyToTextFile.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
