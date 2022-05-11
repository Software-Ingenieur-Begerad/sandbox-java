package de.swingbe.pg_lct_msg_client;

import de.swingbe.pg_lct_msg_client.controller.PgConnection;
import de.swingbe.pg_lct_msg_client.controller.PgPrepStatement;
import de.swingbe.pg_lct_msg_client.model.LctMsg;

public class Main {

    private static final String TABLE = "lct_msg";
    private static final String SCHEMA = "public";

    public static void main(String[] args) {
        System.out.println("main() start...");

        LctMsg lctMsgEdz = new LctMsg("4457006", "534651826", "74635669", "2022-05-10", "13:34:05,824");
        lctMsgEdz.setTenant("EDZ/247");
        System.out.println("" + lctMsgEdz);
        LctMsg lctMsgAlb = new LctMsg("1152427", "527980303", "89021481", "2022-05-10", "13:33:54,789");
        lctMsgAlb.setTenant("ALB/8241");
        System.out.println("" + lctMsgAlb);
        LctMsg lctMsgGer = new LctMsg("9984", "534019410", "83551613", "2022-05-10", "13:33:54,789");
        lctMsgGer.setTenant("GER/201");
        System.out.println("" + lctMsgGer);
        LctMsg lctMsgWol = new LctMsg("0", "530382858", "89636652", "2022-05-09", "10:09:28,654");
        lctMsgWol.setTenant("WOL/238");
        System.out.println("" + lctMsgWol);

        //connection URL for the postgres database
        String host = "host";
        String port = "port";
        String db = "db";
        String usr = "usr";
        String key = "key";

        PgConnection pgCon = new PgConnection(host, port, db, usr, key);

        if (pgCon.getConnection() == null) {
            pgCon.setConnection();
            System.out.println("main() pgCon set");
        }

        PgPrepStatement pgPrepStatement = new PgPrepStatement(pgCon);

        boolean hasTable = false;
        while (!hasTable) {
            hasTable = pgPrepStatement.hasTable(TABLE, SCHEMA);
            if (hasTable) {
                System.out.print("main() has table: ");
                System.out.println("" + TABLE);
            } else {
                System.out.print("main() has NOT table: ");
                System.out.println("" + TABLE);
                pgPrepStatement.createTable(TABLE);
            }
        }

        boolean hasEdz = false;
        while (!hasEdz) {
            hasEdz = pgPrepStatement.hasLctMsg(lctMsgEdz, TABLE);
            if (hasEdz) {
                System.out.print("main() has msg: ");
                System.out.println("" + lctMsgEdz);
            } else {
                System.out.print("main() has NOT msg: ");
                System.out.println("" + lctMsgEdz);
                pgPrepStatement.insert(lctMsgEdz, TABLE);
            }
        }

        System.out.println("main() done.");
        return;
    }
}
