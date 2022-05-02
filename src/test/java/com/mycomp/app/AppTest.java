package com.mycomp.app;

import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest extends BaseTest {
    public AppTest() {
    }
    @Test
    public void testApp(){
        String cmdLine = "-f %s -d %s";
        String date = "2018-12-09";
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String[] args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_SUCCESS_DOUBLE);
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_SUCCESS_KO_MISSING_SESSION);
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_SUCCESS_KO_MISSING_DATE);
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_SUCCESS_KO_BAD_DATE_TIME);
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_INVALID_TYPE);
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        fileName = getProperties(FILE_SUCCESS_DOUBLE);
        date = "10-09-2018";
        args = String.format(cmdLine,fileName,date).split(StringUtils.SPACE);
        App.main(args);

        String cmdLine1 = "-f %s";
        args = String.format(cmdLine1,fileName).split(StringUtils.SPACE);
        App.main(args);

    }
}