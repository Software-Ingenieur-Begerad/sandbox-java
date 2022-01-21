package de.swingbe.ahc;

import org.dedriver.TxFactory;
import org.dedriver.model.Adr;
import org.dedriver.model.Msg;

public class Main {

    /**
     * static String URL = "http://83.223.94.182";
     */
    static String URL = "https://dedriver.org";
    /**
     * static String PORT = "42001";
     */
    static String PORT = "443";
    static String ROUTE = "/postdata";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        TxFactory.createTx().send(new Msg("uuuid", "87.263783", "52.9019052",
                "1642802000000", "alias", "0"), new Adr(PORT, ROUTE, URL));
        System.out.println("Done!");
    }

}