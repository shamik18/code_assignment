package com.mycomp.cmd;

import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.StringUtils;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class CommandLineTest extends BaseTest {
    public CommandLineTest() {
    }
    @Test
    public void testParseInd(){
        String cmd1 = "-f /var/mware -d 2022-10-12";
        String[] args = cmd1.split(StringUtils.SPACE);
        CommandLine commandLine = CommandLine.instance();
        Map<String,String> keyValArgs = commandLine.parseInd(args);
        assertEquals(keyValArgs.get("-f"),"/var/mware");
        assertEquals(keyValArgs.get("-d"),"2022-10-12");

        cmd1 = "-d 2022-10-12 -f /var/mware";
        args = cmd1.split(StringUtils.SPACE);
        keyValArgs = commandLine.parseInd(args);
        assertEquals(keyValArgs.get("-f"),"/var/mware");
        assertEquals(keyValArgs.get("-d"),"2022-10-12");

        cmd1 = "-d 2022-10-12 -f ";
        args = cmd1.split(StringUtils.SPACE);
        keyValArgs = commandLine.parseInd(args);
        assertEquals(keyValArgs.get("-f"),StringUtils.EMPTY);
        assertEquals(keyValArgs.get("-d"),"2022-10-12");

        cmd1 = "-d 2022-10-12 -v ";
        args = cmd1.split(StringUtils.SPACE);
        keyValArgs = commandLine.parseInd(args);
        assertEquals(commandLine.message().commandType(), EnumCmdType.INVALID_IND);

        cmd1 = "-v -d 2022-10-12 ";
        args = cmd1.split(StringUtils.SPACE);
        keyValArgs = commandLine.parseInd(args);
        assertEquals(commandLine.message().commandType(), EnumCmdType.INVALID_IND);

        cmd1 = "-d 2022-10-12 -f /var/mware -cfg";
        args = cmd1.split(StringUtils.SPACE);
        keyValArgs = commandLine.parseInd(args);
        assertEquals(commandLine.message().commandType(), EnumCmdType.INVALID_IND);
    }

    @Test
    public void testParseArgs(){
        String cmd1 = "-f /var/mware -d 2022-10-12";
        String[] args = cmd1.split(StringUtils.SPACE);
        CommandLine commandLine = CommandLine.instance();
        Optional<SessionArgs> optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.LOGICAL_FILE_EXCEPTION);

        cmd1 = "-d 2022-10-12 -f "+getProperties(FILE_SUCCESS_SINGLE);
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertTrue(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.VALID);

        cmd1 = "-d 2022-10-12 -f ";
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.USAGE);

        cmd1 = "-d 2022-10-12 -f     ";
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.USAGE);

        cmd1 = "-d 2022-10-12 -v";
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.USAGE);

        cmd1 = "-v -d 2022-10-12";
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.USAGE);


        cmd1 = "-d 2022-10-12 -f /var/mware -cfg";
        args = cmd1.split(StringUtils.SPACE);
        optional = commandLine.parseArgs(args);
        assertFalse(optional.isPresent());
        assertEquals(commandLine.message().commandType(), EnumCmdType.INVALID_IND);

    }
}