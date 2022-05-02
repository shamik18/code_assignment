package com.mycomp.reader;

import com.mycomp.model.SessionInfo;
import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.ReflectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CustomCSVReaderTest extends BaseTest {
    @Test
    public void testReadNext()  {
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        try {
            CustomCSVReader customCSVReader = new CustomCSVReader(new FileReader(fileName));
            String[] fields = customCSVReader.readNext();
            String[] row1 = new String[]{"cookie","timestamp"};
            Assert.assertArrayEquals(row1,fields);
            fields = customCSVReader.readNext();
            String[] row2 = new String[]{"AtY0laUfhglK3lC7","2018-12-09T14:19:00+00:00"};
            Assert.assertArrayEquals(fields,row2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}