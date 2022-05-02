package com.mycomp.reader;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.ReflectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.stream.Stream;

public class CSVFileReaderTest extends BaseTest {

    public CSVFileReaderTest(){}

    @Test
    public void testCSVReadValid(){
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String[] mapping = ReflectionUtils.getFields(SessionInfo.class);
        try(CSVFileReader<SessionInfo> fileReader = new CSVFileReader<>()){
            Stream<SessionInfo> stream = fileReader.read(fileName,mapping,SessionInfo.class);
            Assert.assertNotNull(stream);
        } catch (IOException | DataSetOperationFailureException e) {
            throw new RuntimeException(e);
        }
    }
}