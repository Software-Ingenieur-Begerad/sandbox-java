package de.swingbe.tailer;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener extends TailerListenerAdapter {
    public final static Logger LOG = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void handle(String line) {
        LOG.debug("{}", line);
    }
}
