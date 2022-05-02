package com.mycomp.app;

import com.mycomp.app.configuration.AppConfig;
import com.mycomp.cmd.CommandLine;
import com.mycomp.cmd.SessionArgs;
import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.service.SessionFreqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {
    private static final AppicationContext appicationContext = new AppicationContext();
    private static final Logger log = LogManager.getLogger(App.class);

    public static AppicationContext appicationContext() {
        return appicationContext;
    }

    public static void main(String[] args) {
        log.debug("Initialization of App Started");
        init();
        log.debug("Initialization of App Started");
        CommandLine commandLine = CommandLine.instance();
        Optional<SessionArgs> optional = commandLine.parseArgs(args);
        Console console = appicationContext.displayConsole();
        Message message = commandLine.message();
        if(optional.isPresent()){
            SessionArgs sessionArgs = optional.get();

            message.setCommandType(EnumCmdType.VALID);
            message.prepareMessage(sessionArgs.fileName(),sessionArgs.searchDate());
            console.setMessage(message).printMessage();

            SessionFreqService sessionFreqService = new SessionFreqService();
            try {
                List<String> sessionList = sessionFreqService.getHighterFreqSession(sessionArgs);
                message.prepareResult(sessionList,sessionArgs.fileName(),sessionArgs.searchDate());
                console.setMessage(message).printMessage();
            } catch (DataSetOperationFailureException e) {
                appicationContext.exceptionHandler().handleException(e, message);
                console.setMessage(message).printMessage();
            }
        }else{
            console.setMessage(commandLine.message()).printMessage();
        }
    }

    private static void init() {
        AppConfig appConfig = new AppConfig();
        appConfig.loadConfig();
        appicationContext().setAppConfig(appConfig);
    }
}
