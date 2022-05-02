package com.mycomp.exception;

import java.io.IOException;

public class DataSetOperationFailureException extends Exception {
    private final String message;
    public DataSetOperationFailureException(IOException e) {
        super(e);
        this.message=String.format("Failed to process the data set due to : %s",e.getCause());
    }

    public String getMessage(){
        return message;
    }
}
