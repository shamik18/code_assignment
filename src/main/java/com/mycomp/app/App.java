package com.mycomp.app;

import com.mycomp.app.configuration.AppConfig;
import com.mycomp.cmd.CommandLine;
import com.mycomp.cmd.SessionArgs;
import com.mycomp.enumtype.EnumCmdType;
import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.service.SessionFreqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * This class represent the application. This application reads the file from
 * command line and find out most favourite session in the file on a particular
 * date.
 */
public class App {
    /**
     * This holds all the context application need . This holds the necessary setup classes that
     * application used for. This act as context and share information across different part of the
     * application.
     */
    private static final ApplicationContext appicationContext = new ApplicationContext();
    /**
     * Log information for the application.
     */
    private static final Logger log = LogManager.getLogger(App.class);

    public static ApplicationContext appicationContext() {
        return appicationContext;
    }

    /**
     * Application entry point.
     * @param args holds command line arguments as an array of string.
     */
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

    /**
     * Initialized the application configuration.
     */
    private static void init() {
        AppConfig appConfig = new AppConfig();
        appConfig.loadConfig();
        appicationContext().setAppConfig(appConfig);
    }
}
