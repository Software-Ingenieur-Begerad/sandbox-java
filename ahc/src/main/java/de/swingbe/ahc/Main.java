package de.swingbe.ahc;

import org.dedriver.TxFactory;
import org.dedriver.model.Adr;
import org.dedriver.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

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
        LOG.debug("Hello world!");
        Msg msg = new Msg("uuuid", "87.263783", "52.9019052",
                "1642802000000", "alias", "0");
        LOG.debug("msg: " + msg);
        Adr adr = new Adr(PORT, ROUTE, URL);
        LOG.debug("adr: " + adr);
        TxFactory.createTx().send(msg, adr);
        LOG.debug("Done!");
    }

}