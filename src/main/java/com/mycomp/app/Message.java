package com.mycomp.app;

import com.mycomp.enumtype.EnumCmdType;

import java.util.List;

public class Message {
    public static final String INVALID_IND = "The %s indicator pass in the command line is not supported";
    public static final String EXCEPTION_DATE_VALIDATION = "The %s not a supported date format ";
    public static final String EXCEPTION_FILE_VALIDATION = "The %s not a valid file or not have proper access.";
    public static final String USEAGE = "The command line parameter are not correct.";
    public static final String GENERAL = "Please provide the input in the following format:\n"
                                        +"> java -jar session_frequency_count.jar -f <filepath> -d <date>\n"
                                        +"As for example if the csv file resides under /usr/apps/session_log.csv and \n"
                                        +"need to find the session ids that are generated under the date say 2022-04-29\n"
                                        +"then the usage is :\n"
                                        +"> java -jar session_frequency_count.jar -f /usr/apps/session_log.csv -d 2022-04-29 \n";
    public static final String ERROR = "Some Unknown error encounter";

    public static final String VALID = "Try to find out maximum occurrence of sessionID in the file:%s on date:%s";

    public static final String RES_DL = "============================================================================";
    public static final String RES_MSG = "The Most Active Cookie  on the date %s ";
    public static final String RES_NO_COUNT = "There no session found in %s on the date %s ";


    private String message;

    private EnumCmdType commandType;


    public EnumCmdType commandType() {
        return commandType;
    }

    public void setCommandType(EnumCmdType commandType) {
        this.commandType = commandType;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void prepareMessage(String... args){
        StringBuilder stringBuilder = new StringBuilder();
        switch (commandType){
            case VALID:
                stringBuilder.append(String.format(VALID,args[0],args[1]));
                stringBuilder.append('\n');
                message = stringBuilder.toString();
                break;
            case INVALID_IND:
                stringBuilder.append(String.format(INVALID_IND,args[0]));
                stringBuilder.append('\n');
                stringBuilder.append('\n');
                stringBuilder.append(GENERAL);
                message = stringBuilder.toString();
                break;
            case LOGICAL_DATE_EXCEPTION:
                stringBuilder.append(String.format(Message.EXCEPTION_DATE_VALIDATION,args[0]));
                stringBuilder.append('\n');
                stringBuilder.append('\n');
                stringBuilder.append(GENERAL);
                message = stringBuilder.toString();
                break;
            case LOGICAL_FILE_EXCEPTION:
                stringBuilder.append(String.format(Message.EXCEPTION_FILE_VALIDATION,args[0]));
                stringBuilder.append('\n');
                stringBuilder.append('\n');
                stringBuilder.append(GENERAL);
                message = stringBuilder.toString();
                break;
            case KNOWN_ERROR:
                stringBuilder.append(Message.ERROR);
                stringBuilder.append('\n');
                stringBuilder.append('\n');
                stringBuilder.append(GENERAL);
                message = stringBuilder.toString();
                break;
            case USAGE:
                stringBuilder.append(Message.USEAGE);
                stringBuilder.append('\n');
                stringBuilder.append('\n');
                stringBuilder.append(GENERAL);
                message = stringBuilder.toString();
        }
    }


    public void prepareResult(List<String> sessionList,String... parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Message.RES_DL);
        stringBuilder.append('\n');
        stringBuilder.append(String.format(Message.RES_MSG,parameters[1]));
        stringBuilder.append('\n');
        stringBuilder.append(Message.RES_DL);
        stringBuilder.append('\n');
        if(sessionList.size()>0){
            for(String sessionID : sessionList){
                stringBuilder.append(sessionID);
                stringBuilder.append('\n');
            }
        }else{
            stringBuilder.append(String.format(RES_NO_COUNT,parameters[0],parameters[1]));
            stringBuilder.append('\n');
        }
        message = stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", commandType=" + commandType +
                '}';
    }

    public void prepareExceptionMessage(String s) {
        message = s;
    }
}
