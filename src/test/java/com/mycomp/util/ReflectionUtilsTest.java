package com.mycomp.util;

import com.mycomp.model.SessionInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class ReflectionUtilsTest {
    public ReflectionUtilsTest(){}
    @Test
    public void testInvoke(){
        SessionInfo sessionInfo = new SessionInfo();
        String sessionDT = "ABDC";
        String sessionID = "1234";
        sessionInfo.setSessionDT(sessionDT);
        sessionInfo.setSessionID(sessionID);

        String resultID = ReflectionUtils.invoke(SessionInfo.class,"sessionID",sessionInfo);
        Assert.assertEquals(sessionID,resultID);
        String resultDT = ReflectionUtils.invoke(SessionInfo.class,"sessionDT",sessionInfo);
        Assert.assertEquals(sessionDT,resultDT);
        Assert.assertThrows(RuntimeException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                ReflectionUtils.invoke(SessionInfo.class,"SessionDT",sessionInfo);
            }
        });
    }

    @Test
    public void testGetFields(){
        String[] expected = new String[]{"sessionID","sessionDT"};
        String[] fields = ReflectionUtils.getFields(SessionInfo.class);
        Assert.assertArrayEquals(fields,expected);
    }

}