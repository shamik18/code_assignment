package com.mycomp.cmd;

import com.mycomp.app.Message;
import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.util.DateTimeUtils;
import com.mycomp.util.FIleUtils;

import java.util.Optional;

/**
 * This is the builder class for SessionArgs.
 */
public class SessionArgsBuilder {
    private String fileName;
    private String date;
    private static final SessionArgsBuilder _insSessionArgsBuilder = new SessionArgsBuilder();
    private Message message;
    private SessionArgsBuilder(){}
    public static SessionArgsBuilder getInstance(){
        return _insSessionArgsBuilder;
    }

    /**
     * This method ensure mandatory fields that needed by the application should set.
     * @param fileName file name (mandatory)
     * @param date date to filter (mandatory)
     * @return self for method chaining.
     */
    public SessionArgsBuilder setMandatoryParameter(String fileName, String date){
        this.fileName = null;
        if(FIleUtils.isValidFile(fileName)){
            this.fileName = fileName;
        }else{
            message.setCommandType(EnumCmdType.LOGICAL_FILE_EXCEPTION);
            message.prepareMessage(fileName);
        }
        this.date = null;
        if(DateTimeUtils.validDate(date)){
            this.date = date;
        }else{
            message.setCommandType(EnumCmdType.LOGICAL_DATE_EXCEPTION);
            message.prepareMessage(date);
        }
        return this;
    }

    /**
     * Hold the message that need to generate at the time of object creation.
     * @param message as input.
     * @return self for method chaining.
     */
    public SessionArgsBuilder withMessage(Message message){
        this.message = message;
        return this;
    }

    /**
     * Construct instance of SessionArgs
     * @return instance of SessionArgs.
     */
    public Optional<SessionArgs> build(){
        if(this.fileName != null && this.date != null){
            SessionArgs sessionArgs = new SessionArgs(this.fileName,this.date);
            return Optional.of(sessionArgs);
        }
        return Optional.empty();
    }

    /**
     * Return the message from the builder.
     * @return Message
     */
    public Message getMessage(){
        return message;
    }
}
