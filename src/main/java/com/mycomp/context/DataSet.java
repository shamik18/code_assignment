package com.mycomp.context;

import com.mycomp.cmd.CommandLine;
import com.mycomp.reader.CSVFileReader;
import com.mycomp.util.ReflectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSet<T> implements IDataSet<T>, Closeable {
    private static final Logger log = LogManager.getLogger(DataSet.class);
    private Stream<T> stream;
    private final CSVFileReader<T> csvFileReader;

    public DataSet(Stream<T> stream,CSVFileReader<T> csvFileReader){
        this.stream = stream;
        this.csvFileReader = csvFileReader;
    }

    @Override
    public IDataSet<T> filter(Predicate<? super T> predicate) {
        stream = stream.filter(predicate);
        return this;
    }

    @Override
    public List<String> maxFrequencyKeys(String keyName) {
        Map<Object,Long> map = stream.collect(Collectors.groupingBy(t -> ReflectionUtils.invoke(t.getClass(),keyName,t),Collectors.counting()));
        log.debug("maxFrequencyKeys::map = {}",map);
        return getHighFrequencyFields(map);
    }

    public List<String> getHighFrequencyFields(Map<Object,Long> map){
        long max = -1;
        List<String> fields = new ArrayList<>();
        for(Map.Entry<Object,Long> entry: map.entrySet()){
            if(entry.getValue() >max){
                max = entry.getValue();
                fields.clear();
                fields.add((String) entry.getKey());
            }else if( entry.getValue() == max){
                fields.add((String) entry.getKey());
            }
        }
        log.debug("Frequency list.size():{}", fields.size());
        return fields;
    }

    @Override
    public void close() throws IOException {
        if(null != csvFileReader){
            csvFileReader.close();
        }
    }
}
