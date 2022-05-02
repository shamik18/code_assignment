package com.mycomp.reader;

import com.mycomp.exception.DataSetOperationFailureException;

import java.util.stream.Stream;

public interface ICSVFileReader<T> {
    Stream<T> read(String location,String[] mapping, Class<T> tClass) throws DataSetOperationFailureException;
}
