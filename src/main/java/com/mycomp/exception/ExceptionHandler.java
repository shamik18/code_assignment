package com.mycomp.exception;

import com.mycomp.app.Message;

/**
 * Handle all exception raised in the application.
 */
public class ExceptionHandler {
    public void handleException(Exception e, Message message){
        if(e instanceof DataSetOperationFailureException){
            message.prepareExceptionMessage("Data Set Exception Occur:"+e.getMessage()+"\n");
        }
    }
}
