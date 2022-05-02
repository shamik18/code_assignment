package com.mycomp.testhelper;

public class BaseTest {
    protected static final String FILE_SUCCESS_SINGLE ="FILE_SUCCESS_SINGLE";
    protected static final String FILE_SUCCESS_DOUBLE ="FILE_SUCCESS_DOUBLE";
    protected static final String FILE_SUCCESS_KO_MISSING_SESSION ="FILE_SUCCESS_KO_MISSING_SESSION";
    protected static final String FILE_SUCCESS_KO_MISSING_DATE = "FILE_SUCCESS_KO_MISSING_DATE";
    protected static final String FILE_SUCCESS_KO_BAD_DATE_TIME = "FILE_SUCCESS_KO_BAD_DATE_TIME";
    protected static final String FILE_INVALID_TYPE = "FILE_INVALID_TYPE";

    public static final String SEARCH_DATA = "2018-12-09";
    private static ConfigUtils configUtils = new ConfigUtils();
    static {
        configUtils.load();
    }
    public String getProperties(String name){
        return configUtils.properties().getProperty(name);
    }
}
