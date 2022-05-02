package com.mycomp.context;

import java.util.List;
import java.util.function.Predicate;

public interface IDataSet<T> {
    IDataSet<T> filter(Predicate<? super T> predicate);
    List<String> maxFrequencyKeys(String keyName);
}
