package com.mycomp.app;

import com.mycomp.app.configuration.AppConfig;
import com.mycomp.exception.ExceptionHandler;

/**
 * Application Context hold the context of the application and share information
 * at application level.
 */
public class ApplicationContext {
    /**
     * Hold application configuration as key-value pair.
     */
    private AppConfig appConfig;
    /**
     * Hold instance of the Console class to display the messages to
     * display destination like console.
     */
    private final Console displayConsole = new Console();
    /**
     * Handle all exception generated in the code.
     */
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();


    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public AppConfig appConfig() {
        return appConfig;
    }

    public Console displayConsole() {
        return displayConsole;
    }

    public ExceptionHandler exceptionHandler() {
        return exceptionHandler;
    }
}
