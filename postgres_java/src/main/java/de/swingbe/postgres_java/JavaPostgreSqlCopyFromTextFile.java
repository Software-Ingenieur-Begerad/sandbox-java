package de.swingbe.postgres_java;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlCopyFromTextFile {

    public static void main(String[] args) {

        //connection URL for the postgres database
        //jdbc:postgresql://<host>:<port>/<database name>
        String url = "jdbc:postgresql://localhost:5432/testdb";

        String user = "usr";
        String password = "#password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            CopyManager cm = new CopyManager((BaseConnection) con);

            String fileName = "src/main/resources/friends.txt";

            try (FileInputStream fis = new FileInputStream(fileName); InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {

                cm.copyIn("COPY friends FROM STDIN WITH DELIMITER ','", isr);
            }

        } catch (SQLException | IOException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSqlCopyFromTextFile.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }
}