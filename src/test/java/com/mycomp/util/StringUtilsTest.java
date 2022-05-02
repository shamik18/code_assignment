package com.mycomp.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    public StringUtilsTest() {
    }
    @Test
    public void testLength(){
        assertEquals(StringUtils.length(null),0);
        assertEquals(StringUtils.length(StringUtils.EMPTY),0);
        assertNotEquals(StringUtils.length(StringUtils.SPACE),0);
    }

    @Test
    public void testBlankOrEmpty(){
        assertTrue(StringUtils.blankOrEmpty(null));
        assertTrue(StringUtils.blankOrEmpty(StringUtils.EMPTY));
        assertTrue(StringUtils.blankOrEmpty(StringUtils.SPACE));
        assertFalse(StringUtils.blankOrEmpty("ABCD"));
        assertFalse(StringUtils.blankOrEmpty(" ABCD    "));
    }

    @Test
    public void testGetStringFromStringArray(){
        String commandLine = "-f /var/good/hello world/test.csv -d 2018-12-09";
        String[] args = commandLine.split(StringUtils.SPACE);
        String filePath = StringUtils.getStringFromStringArray(args,"-f","-d");
        assertEquals(filePath,"/var/good/hello world/test.csv");
        commandLine = "-f /var/good/inputs/test.csv -d 2018-12-09";
        args = commandLine.split(StringUtils.SPACE);
        filePath = StringUtils.getStringFromStringArray(args,"-f","-d");
        assertEquals(filePath,"/var/good/inputs/test.csv");

        commandLine = "-d 2018-12-09";
        args = commandLine.split(StringUtils.SPACE);
        filePath = StringUtils.getStringFromStringArray(args,"-d");
        assertEquals(filePath,"2018-12-09");

        commandLine = "-d 2018-12-09 abcd";
        args = commandLine.split(StringUtils.SPACE);
        filePath = StringUtils.getStringFromStringArray(args,"-d");
        assertEquals(filePath,"2018-12-09 abcd");

        commandLine = "-d 2018-12-09 -f /var/a.csv -cfg conf.properties";
        args = commandLine.split(StringUtils.SPACE);
        filePath = StringUtils.getStringFromStringArray(2,args,"-f","-cfg");
        assertEquals(filePath,"/var/a.csv");

    }

    @Test
    public void testRemoveQuotesString(){
        String singleQuotes = "'2018-12-09'";
        String resStr = StringUtils.removeQuotesString(singleQuotes);
        assertEquals(resStr,"2018-12-09");

        String dblQuotes = "\"2018-12-09\"";
        resStr = StringUtils.removeQuotesString(dblQuotes);
        assertEquals(resStr,"2018-12-09");

        String noQuotes = "2018-12-09";
        resStr = StringUtils.removeQuotesString(noQuotes);
        assertEquals(resStr,"2018-12-09");
    }
}