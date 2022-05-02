package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;

public interface IDataContext<T> {
    DataSet<T> readFromCSV(String fileLocation, Class<T> tClass, String[] mapper) throws DataSetOperationFailureException;
}
