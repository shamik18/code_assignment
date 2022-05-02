package com.mycomp.util;

import com.mycomp.testhelper.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FIleUtilsTest extends BaseTest {
    public FIleUtilsTest() {
    }
    @Test
    public void testValidFile(){
        String fileValid = getProperties(FILE_SUCCESS_SINGLE);
        assertTrue(FIleUtils.isValidFile(fileValid));
        String validDir = "/home";
        assertFalse(FIleUtils.isValidFile(validDir));
        String inValidDir = "/var/mware";
        assertFalse(FIleUtils.isValidFile(inValidDir));
        String invalidFile = "/home/abcd.txt";
        assertFalse(FIleUtils.isValidFile(invalidFile));
        assertFalse(FIleUtils.isValidFile(getProperties(FILE_INVALID_TYPE)));
        assertFalse(FIleUtils.isValidFile(StringUtils.EMPTY));
    }
}