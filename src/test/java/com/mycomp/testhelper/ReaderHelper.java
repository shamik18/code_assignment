package com.mycomp.testhelper;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.reader.CSVFileReader;
import com.mycomp.util.ReflectionUtils;

import java.io.IOException;
import java.util.stream.Stream;

public class ReaderHelper extends BaseTest {
    static CSVFileReader<SessionInfo> fileReader;
    public Stream<SessionInfo> getStream() throws DataSetOperationFailureException {
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String[] mapping = ReflectionUtils.getFields(SessionInfo.class);
        fileReader = new CSVFileReader<>();
        Stream<SessionInfo> stream = fileReader.read(fileName,mapping,SessionInfo.class);
        return stream;
    }

    public static void close() throws IOException {
        if(null != fileReader){
            fileReader.close();
        }
    }
}
