package de.begerad.duprm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static de.begerad.duprm.DuplicateCleaner.clean;

public class Main {

    public static String pathInput = "";

    public static String pathOutput = "";

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("main() started...");

        //check user input for input path
        if (args.length < 1) {
            System.out.println("Please enter input path as first parameter.");
            return;
        }

        pathInput = args[0];
        if (!(new File(pathInput).isFile())) {
            System.out.println("parameter: "
                    + pathInput
                    + " is NOT a valid path.");
            return;
        }

        //check user input for output path
        if (args.length < 2) {
            System.out.println("Please enter output path as second parameter.");
            return;
        }

        pathOutput = args[1];
        //call duplicate cleaner
        clean(pathInput, pathOutput);

        LOG.debug("main() done.");
    }
}
