package com.mycomp.reader;

import com.mycomp.exception.DataSetOperationFailureException;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.IterableCSVToBean;
import com.opencsv.bean.IterableCSVToBeanBuilder;

import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CSVFileReader<T> implements ICSVFileReader<T>, Closeable {
    ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
    CustomCSVReader csvReader;
    @Override
    public Stream<T> read(String location, String[] mapping, Class<T> clazz) throws DataSetOperationFailureException {
        Stream<T> stream = null;
        try{
           if(csvReader!=null){
               csvReader.close();
           }
           csvReader = new CustomCSVReader(new FileReader(location));
           strategy.setType(clazz);
           strategy.setColumnMapping(mapping);
           IterableCSVToBean<T> iterableCSVToBean = new IterableCSVToBeanBuilder<T>().withReader(csvReader).withMapper(strategy).build();
           CustomSpliterator<T> customSpliterator= new CustomSpliterator<>(iterableCSVToBean);
           stream = StreamSupport.stream(customSpliterator,true);
        } catch (IOException e) {
            throw new DataSetOperationFailureException(e);
        }
        return stream;
    }

    @Override
    public void close() throws IOException {
        if(null != this.csvReader){
            csvReader.close();
        }
    }
}
