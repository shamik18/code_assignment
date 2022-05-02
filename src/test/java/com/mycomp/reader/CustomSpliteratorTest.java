package com.mycomp.reader;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.testhelper.ReaderHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.IOException;
import java.util.stream.Stream;

public class CustomSpliteratorTest {

    @Test
    public void tryAdvance() throws IOException, DataSetOperationFailureException {
        Stream<SessionInfo> stream = new ReaderHelper().getStream();
        stream.forEach(System.out::println);
        ReaderHelper.close();

        Assert.assertThrows(IllegalStateException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                stream.forEach(System.out::println);
            }
        });
    }

    @Test
    public void trySplit() {
    }
}