package lt.seb;

import lt.seb.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class DateUtilsTest {

    private DateUtils dateUtils;

    @Before
    public void init() {
        dateUtils = new DateUtils();
    }

    @Test
    public void testDateIsNull() {
        Assert.assertEquals(dateUtils.validate(null), "Date is not selected!");
    }

    @Test
    public void testDateIsInvalid() {
        Assert.assertEquals(dateUtils.validate("28/02/2012"), "Date format not supported");
    }

    @Test
    public void testDateIsGreaterThan() {
        Assert.assertEquals(dateUtils.validate("2015-01-01"), "Currencies rates are only available till - " + DateUtils.maxDateString);
    }

    @Test
    public void testCorrectDate() {
        Assert.assertEquals(dateUtils.validate("2014-01-01"), null);
    }

    @Test
    public void testDateBefore() throws ParseException {
        Assert.assertEquals(dateUtils.getDateBefore("2014-03-03"), "2014-03-02");
    }

}
