package com.mycomp.model;

import java.util.Objects;

public class SessionInfo {
    private String sessionID;
    private String sessionDT;


    public String sessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String sessionDT() {
        return sessionDT;
    }

    public void setSessionDT(String sessionDT) {
        this.sessionDT = sessionDT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionInfo)) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(sessionID, that.sessionID) && Objects.equals(sessionDT, that.sessionDT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, sessionDT);
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "sessionID='" + sessionID + '\'' +
                ", sessionDT='" + sessionDT + '\'' +
                '}';
    }
}
