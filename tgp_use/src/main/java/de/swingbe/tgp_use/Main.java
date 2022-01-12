package de.swingbe.tgp_use;

import de.swingbe.ifleet.model.Entity;
import de.swingbe.ifleet.parser.EntityParserFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Entity entity = EntityParserFactory.createEntityParser().parse("TODO");
        if (entity != null) {
            System.out.println("entity: " + entity);
        }

        entity = EntityParserFactory.createEntityParser().parse("T2021-11-30 11:35:44,553 INFO  [BON /195.30.103.89:58961] (NpmTCPAcceptedChannel) (/195.30.103.89:58961) sent <I#EDZ/433#B##1#1.8#1#9406501H#0#74340429#536460268#0###9235#2081#4314#4314006##0#-7#160#42180#0#5.>");
        if (entity != null) {
            System.out.println("entity: " + entity);
        }

        entity = EntityParserFactory.createEntityParser().parse("2021-11-30 11:24:13,438 INFO  [BON /195.30.103.89:58713] (NpmTCPAcceptedChannel) (/195.30.103.89:58713) sent <I#WOL/234#B##1#1.8#1#1501303H#280#87263783#529019052#0#420#162#29#711#1101#1101099##452#0#2#37500#0#6.>");
        if (entity != null) {
            System.out.println("entity: " + entity);
        }

        System.out.println("Done!");
    }
}
