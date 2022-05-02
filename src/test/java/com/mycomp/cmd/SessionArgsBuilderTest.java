package com.mycomp.cmd;

import com.mycomp.app.Message;
import com.mycomp.testhelper.BaseTest;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SessionArgsBuilderTest extends BaseTest {

    @Test
    public void getInstance() {
        assertNotNull(SessionArgsBuilder.getInstance());
        assertEquals(SessionArgsBuilder.getInstance(),SessionArgsBuilder.getInstance());
    }

    @Test
    public void setMandatoryParameter() {
        assertEquals(SessionArgsBuilder.getInstance().setMandatoryParameter("asasa","daaa"),SessionArgsBuilder.getInstance());
    }

    @Test
    public void build() {
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String date = "2022-04-29";
        Message message = new Message();
        Optional<SessionArgs> optional = SessionArgsBuilder.getInstance().withMessage(message).setMandatoryParameter(fileName,date).build();
        assertTrue(optional.isPresent());

        String fileName2 = "/var/mware";
        String date2 = "2022-04-29";
        Optional<SessionArgs> optional2 = SessionArgsBuilder.getInstance().withMessage(message).setMandatoryParameter(fileName2,date2).build();
        assertFalse(optional2.isPresent());
    }
}