package com.mycomp.app;

import com.mycomp.app.configuration.AppConfig;
import com.mycomp.exception.ExceptionHandler;

public class AppicationContext {
    private AppConfig appConfig;
    private Console displayConsole = new Console();
    private ExceptionHandler exceptionHandler = new ExceptionHandler();


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
