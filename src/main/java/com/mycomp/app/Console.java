package com.mycomp.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class responsible to display the message to console.
 */
public class Console {
    private static final Logger log = LogManager.getLogger(Console.class);
    /**
     * This holds the instance of message.
     */
    private Message message;

    public Console setMessage(Message message) {
        this.message = message;
        return this;
    }

    public void printMessage(){
        System.out.println(message.message());
    }
}
