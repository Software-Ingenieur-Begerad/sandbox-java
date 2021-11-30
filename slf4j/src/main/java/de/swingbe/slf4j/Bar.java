package de.swingbe.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {
    public final static Logger LOG = LoggerFactory.getLogger(Bar.class);

    public boolean doIt() {
        LOG.error("Did it again!");
        return false;
    }
}
