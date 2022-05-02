package com.mycomp.context;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface represent dataset over which aggregation operation will be performed.
 * @param <T> type of the data set.
 */
public interface IDataSet<T> {
    /**
     * Filter the dataset based on predicate.
     * @param predicate used for the filtration
     * @return filtered data set.
     */
    IDataSet<T> filter(Predicate<? super T> predicate);
    List<String> maxFrequencyKeys(String keyName);
}
