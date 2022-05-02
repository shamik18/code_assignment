package com.mycomp.reader;

import com.opencsv.bean.IterableCSVToBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomSpliterator<T> implements Spliterator<T> {
    private static Logger log = LogManager.getLogger(CustomSpliterator.class);
    protected IterableCSVToBean<T> iterableCSVToBean;
    protected Exception exception ;

    CustomSpliterator(IterableCSVToBean<T> iterableCSVToBean){
        this.iterableCSVToBean = iterableCSVToBean;
    }
    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        try{
            T nextBean = iterableCSVToBean.nextLine();
            if(null != nextBean){
                consumer.accept(nextBean);
                return true;
            }
        }catch (Exception exception){
          log.error("Error occur in the stream while reading data.");
          return false;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return DISTINCT | NONNULL | IMMUTABLE;
    }
}
