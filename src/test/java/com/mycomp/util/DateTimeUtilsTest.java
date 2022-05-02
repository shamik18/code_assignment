package com.mycomp.util;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilsTest {
    public DateTimeUtilsTest(){}
    @Test
    public void testDateComparison(){
        String date = "2018-12-09";
        String dateTime = "2018-12-09T10:13:00+00:00";
        Assert.assertTrue(DateTimeUtils.isEquals(date,dateTime));
        date = "2018-12-08";
        dateTime = "2018-12-09T10:13:00+00:00";
        Assert.assertFalse(DateTimeUtils.isEquals(date,dateTime));

        date = "2018-12-08";
        dateTime = null;
        Assert.assertFalse(DateTimeUtils.isEquals(date,dateTime));

        date = null;
        dateTime = "2018-12-09T10:13:00+00:00";
        Assert.assertFalse(DateTimeUtils.isEquals(date,dateTime));

        date = null;
        dateTime = null;
        Assert.assertFalse(DateTimeUtils.isEquals(date,dateTime));

        date = "session";
        dateTime = "time";
        Assert.assertFalse(DateTimeUtils.isEquals(date,dateTime));
    }

    @Test
    public void testValidDate(){
        String  validDate = "2018-12-08";
        String  invalidDate = "08-12-2018";

        Assert.assertTrue(DateTimeUtils.validDate(validDate));
        Assert.assertFalse(DateTimeUtils.validDate(invalidDate));
    }

    @Test
    public void testValidDateTime(){
        String  validDateTime = "2018-12-09T14:19:00+00:00";
        String  invalidDate = "09-12-2018T14:19:00+00:00";

        Assert.assertTrue(DateTimeUtils.validDateTime(validDateTime));
        Assert.assertFalse(DateTimeUtils.validDateTime(invalidDate));
    }

}