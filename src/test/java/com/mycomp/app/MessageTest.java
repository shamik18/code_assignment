package com.mycomp.app;

import com.mycomp.enumtype.EnumCmdType;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MessageTest {
    public MessageTest() {
    }
    @Test
    public void testPrepareMessage(){
        Message message = new Message();
        message.setCommandType(EnumCmdType.VALID);
        String fileName = "/var/mware/abc.csv";
        String date = "2022-04-29";
        message.prepareMessage(fileName,date);
        System.out.println(message.message());
        assertTrue(message.message().contains("Try to find out maximum occurrence of sessionID in the file:/var/mware/abc.csv on date:2022-04-29"));

        message.setCommandType(EnumCmdType.INVALID_IND);
        String invalidArg = "-v";
        message.prepareMessage(invalidArg);
        System.out.println(message.message());
        assertTrue(message.message().contains("The -v indicator pass in the command line is not supported"));

        message.setCommandType(EnumCmdType.LOGICAL_FILE_EXCEPTION);
        fileName = "/var/mware/abc.csv";
        message.prepareMessage(fileName);
        System.out.println(message.message());
        assertTrue(message.message().contains("The /var/mware/abc.csv not a valid file or not have proper access"));

        message.setCommandType(EnumCmdType.LOGICAL_DATE_EXCEPTION);
        message.prepareMessage(date);
        System.out.println(message.message());
        assertTrue(message.message().contains("The 2022-04-29 not a supported date format"));

        message.setCommandType(EnumCmdType.KNOWN_ERROR);
        message.prepareMessage();
        System.out.println(message.message());
        assertTrue(message.message().contains("Some Unknown error encounter"));


        message.setCommandType(EnumCmdType.USAGE);
        message.prepareMessage();
        System.out.println(message.message());//The command line parameter are not correct
        assertTrue(message.message().contains("The command line parameter are not correct"));


        message.prepareResult(Arrays.asList("AtY0laUfhglK3lC7", "SAZuXPGUrfbcn5UA"),fileName,date);
        System.out.println(message.message());//The command line parameter are not correct
        assertTrue(message.message().contains("AtY0laUfhglK3lC7"));

    }
}