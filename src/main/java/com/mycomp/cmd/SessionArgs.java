package com.mycomp.cmd;

public class SessionArgs {
    private final String  fileName;
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
