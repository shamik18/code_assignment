package com.mycomp.app;

import com.mycomp.enumtype.EnumCmdType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Console {
    private static Logger log = LogManager.getLogger(Console.class);

    private Message message;

    public Console setMessage(Message message) {
        this.message = message;
        return this;
    }

    public void printMessage(){
        System.out.println(message.message());
    }
}
