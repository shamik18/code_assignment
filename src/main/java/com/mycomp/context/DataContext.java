package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.reader.CSVFileReader;
import java.util.stream.Stream;

/**
 * This is context class prepare the data set.
 * @param <T> Generic type of the DataSet
 */
public class DataContext<T> implements IDataContext<T> {
    /**
     * Check the definition in interface.
     */
    @Override
    public DataSet<T> readFromCSV(String fileLocation, Class<T> tClass, String[] mapper) throws DataSetOperationFailureException {
        CSVFileReader<T> csvFileReader = new CSVFileReader<>();
        Stream<T> stream = csvFileReader.read(fileLocation,mapper,tClass);
        return new DataSet<>(stream,csvFileReader);
    }
}
