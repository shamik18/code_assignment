package com.mycomp.cmd;

import com.mycomp.app.Message;
import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.util.DateTimeUtils;
import com.mycomp.util.FIleUtils;

import java.util.Optional;

public class SessionArgsBuilder {
    private String fileName;
    private String date;
    private static final SessionArgsBuilder _insSessionArgsBuilder = new SessionArgsBuilder();
    private Message message;
    private SessionArgsBuilder(){}
    public static SessionArgsBuilder getInstance(){
        return _insSessionArgsBuilder;
    }

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
    public SessionArgsBuilder withMessage(Message message){
        this.message = message;
        return this;
    }

    public Optional<SessionArgs> build(){
        if(this.fileName != null && this.date != null){
            SessionArgs sessionArgs = new SessionArgs(this.fileName,this.date);
            return Optional.of(sessionArgs);
        }
        return Optional.empty();
    }

    public Message getMessage(){
        return message;
    }
}
