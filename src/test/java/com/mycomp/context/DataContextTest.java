package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.reader.CSVFileReader;
import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.ReflectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.prefs.BackingStoreException;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DataContextTest extends BaseTest {
    public DataContextTest(){}
    @Test
    public void testReadFromCSV() throws DataSetOperationFailureException {
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String[] mapping = ReflectionUtils.getFields(SessionInfo.class);
        DataContext<SessionInfo> dataContext = new DataContext<>();
        DataSet<SessionInfo> dataSet = dataContext.readFromCSV(fileName,SessionInfo.class,mapping);
        Assert.assertNotNull(dataSet);
    }
}