package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;

public interface IDataContext<T> {
    /**
     * Create dataset from csv data. Read the csv data and create the dataset.
     * @param fileLocation location of the csv file.
     * @param tClass class type of the dataset.
     * @param mapper csv column to bean mapper.
     * @return DataSet
     * @throws DataSetOperationFailureException in case any exception reading the stream.
     */
    DataSet<T> readFromCSV(String fileLocation, Class<T> tClass, String[] mapper) throws DataSetOperationFailureException;
}
