package com.mycomp.exception;

import java.io.IOException;

/**
 * This is the custom exception throws in case of data set stream error.
 */
public class DataSetOperationFailureException extends Exception {
    private final String message;
    public DataSetOperationFailureException(IOException e) {
        super(e);
        this.message=String.format("Failed to process the data set due to : %s",e.getMessage());
    }

    public String getMessage(){
        return message;
    }
}
