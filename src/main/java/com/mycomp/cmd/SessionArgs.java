package com.mycomp.cmd;

/**
 * Class hold the command line argument as a bean.
 */
public class SessionArgs {
    /**
     * Hold the csv file name that application will look into.
     */
    private final String  fileName;
    /**
     * Hold the date as a sting in YYYY-MM-DD format as a filter.
     */
    private final String  searchDate;

    public SessionArgs(String fileName, String searchDate) {
        this.fileName = fileName;
        this.searchDate = searchDate;
    }

    public String fileName() {
        return fileName;
    }

    public String searchDate() {
        return searchDate;
    }

    @Override
    public String toString() {
        return "SessionArgs{" +
                "fileName='" + fileName + '\'' +
                ", searchDate='" + searchDate + '\'' +
                '}';
    }
}
