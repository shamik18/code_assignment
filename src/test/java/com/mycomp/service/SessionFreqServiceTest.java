package com.mycomp.service;

import com.mycomp.cmd.SessionArgs;
import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.testhelper.BaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionFreqServiceTest extends BaseTest {
    public SessionFreqServiceTest(){}
    private SessionFreqService sessionFreqService ;
    @Before
    public void init(){
        sessionFreqService = new SessionFreqService();
    }
    @Test
    public void testGetHighterFreqSession() throws DataSetOperationFailureException {
        //case 1: single session duplicate
        SessionArgs sessionArgs = generateSessionArgs(SEARCH_DATA,getProperties(FILE_SUCCESS_SINGLE));
        List<String> fields = sessionFreqService.getHighterFreqSession(sessionArgs);
        assertEquals(1,fields.size());
        assertEquals(fields.get(0),"AtY0laUfhglK3lC7");
        //case 2: multiple session have highest frequency.
        sessionArgs = generateSessionArgs(SEARCH_DATA,getProperties(FILE_SUCCESS_DOUBLE));
        fields = sessionFreqService.getHighterFreqSession(sessionArgs);
        assertEquals(2,fields.size());
        Set<String> set = new HashSet<>(Arrays.asList("AtY0laUfhglK3lC7","SAZuXPGUrfbcn5UA"));
        for(String f:fields){
            assertTrue(set.contains(f));
            set.remove(f);
        }
        assertEquals(0,set.size());
        //case 3: session is missing
        sessionArgs = generateSessionArgs(SEARCH_DATA,getProperties(FILE_SUCCESS_KO_MISSING_SESSION));
        fields = sessionFreqService.getHighterFreqSession(sessionArgs);
//        assertEquals(2,fields.size());
//        Set<String> set = new HashSet<>(Arrays.asList("AtY0laUfhglK3lC7","SAZuXPGUrfbcn5UA"));
//        for(String f:fields){
//            assertTrue(set.contains(f));
//            set.remove(f);
//        }

    }

    private SessionArgs generateSessionArgs(String searchDate, String fileName){
        return new SessionArgs(fileName,searchDate);
    }
}