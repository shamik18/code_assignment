package com.mycomp.cmd;

import com.mycomp.app.App;
import com.mycomp.app.Message;
import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 *  This class parse the command line argument.
 */
public final class CommandLine {
    private static final Logger log = LogManager.getLogger(CommandLine.class);
    // Constant declaration.
    public static final char HYP_CH = '-';
    public static final String FILE_IND = "-f";
    public static final String DATE_IND = "-d";
    /**
     * Holds as lookup for all the supported command line indicator prefix characters.
     */
    private static final Set<String> supportedInd = new HashSet<>(Arrays.asList(FILE_IND, DATE_IND));
    /**
     * Hold the message going to populate during parsing.
     */
    private Message message = new Message();
    /**
     * Instance of command line.
     * Future can be instantiated as injectable manner to reduce the instance coupling.
     */
    private static final CommandLine _INSTANCE = new CommandLine();

    private CommandLine(){}

    /**
     * Create singleton instance.
     * @return instance of CommandLine
     */
    public static CommandLine instance(){
        return _INSTANCE;
    }

    /**
     * Getter for message
     * @return message as string.
     */
    public Message message() {
        return message;
    }

    /**
     * Parse the command line argument and return as string.
     * @param args command line args
     * @return Optional of SessionArgs that hold the argument as bean.
     */
    public  Optional<SessionArgs>  parseArgs(String[] args) {
        log.debug("Parsing command line arguments.....");
        if(args.length < 4){
            log.debug("Argument length is less than 4 !!");
            message.setCommandType(EnumCmdType.USAGE);
            message.prepareMessage();
            return Optional.empty();
        }
        log.debug("Start parsing arguments");
        Map<String,String> keyValParam = parseInd(args);
        log.debug("After parsing arg Map:{}",keyValParam);
        if(keyValParam == null)
            return Optional.empty();
        String filePath = keyValParam.get(FILE_IND);
        String date = keyValParam.get(DATE_IND);
        log.debug("application received file={}, date={}",filePath,date);
        Optional<SessionArgs> optional = SessionArgsBuilder.getInstance().withMessage(message).setMandatoryParameter(filePath,date).build();
        this.message = SessionArgsBuilder.getInstance().getMessage();
        log.debug("After parsing message={}",this::message);
        return optional;
    }

    public Map<String, String> parseInd(String[] args) {
        Map<String,String> keyValArgs = new HashMap<>();

       message.setCommandType(EnumCmdType.VALID);
        int prevIndex , currentIndex = -1;
        String lastIndicator = null ;

        for(int index=0; index < args.length; index++){
            if(supportedInd.contains(args[index])){
                lastIndicator = args[index];
                prevIndex = currentIndex;
                currentIndex = index;
                if(prevIndex != -1 ){
                    keyValArgs.put(args[prevIndex],StringUtils.getStringFromStringArray(args,prevIndex,currentIndex));
                }
            }else if(StringUtils.beginWith(args[index], HYP_CH)){
                message.setCommandType(EnumCmdType.INVALID_IND);
                message.prepareMessage(args[index]);
                return null;
            }
        }

        keyValArgs.put(lastIndicator,StringUtils.getStringFromStringArray(args,currentIndex));
        return keyValArgs;
    }
}
