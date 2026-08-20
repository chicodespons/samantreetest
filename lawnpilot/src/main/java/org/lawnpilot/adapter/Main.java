package org.lawnpilot.adapter;


import org.lawnpilot.application.LawnMowerApplication;

public class Main {

    public static void main(String[] args) {
        //If you want to run the application by reading from a file,
        // add the absolute path of the file to program arguments
        // otherwise the application will use the default data: 5 5, 1 2 N, LFLFLFLFF, 3 3 E, FFRFFRFRRF
        LawnMowerApplication application = new LawnMowerApplication();
        application.run(args);
        }
    }
