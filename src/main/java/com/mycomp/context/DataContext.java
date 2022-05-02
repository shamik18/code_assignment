package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.reader.CSVFileReader;
import java.util.stream.Stream;

public class DataContext<T> implements IDataContext<T> {
    @Override
    public DataSet<T> readFromCSV(String fileLocation, Class<T> tClass, String[] mapper) throws DataSetOperationFailureException {
        CSVFileReader<T> csvFileReader = new CSVFileReader<>();
        Stream<T> stream = csvFileReader.read(fileLocation,mapper,tClass);
        return new DataSet<T>(stream,csvFileReader);
    }
}
