package com.mycomp.exception;

import com.mycomp.app.Message;

public class ExceptionHandler {
    public void handleException(Exception e, Message message){
        if(e instanceof DataSetOperationFailureException){
            message.prepareExceptionMessage("Data Set Exception Occur:"+e.getMessage()+"\n");
        }
    }
}
