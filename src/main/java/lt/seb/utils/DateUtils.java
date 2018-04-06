package lt.seb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String minDateString = "1993-06-25";
    public static final String maxDateString = "2014-12-31";
    public static final String dateFormat = "yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormat;
    public static Date minDate;
    public static Date maxDate;

    static
    {
        simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            minDate = simpleDateFormat.parse(minDateString);
            maxDate = simpleDateFormat.parse(maxDateString);
        }
        catch (ParseException e) {
            maxDate = null;
        }
    }

    /**
     * validates date field, checks if it is not empty, in correct fomat and is till 2014-12-31
     * @param date
     * @return
     */
    public String validate(String date) {

        if (date == null || date.length() <= 0) {
            return "Date is not selected!";
        }

        //if not valid, it will throw ParseException
        Date realDate = null;
        try {
            realDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return "Date format not supported";
        }

        if (realDate.before(minDate)) {
            return "Currencies rates are only available from - " + minDateString;
        }

        if (realDate.after(maxDate)) {
            return "Currencies rates are only available till - " + maxDateString;
        }

        return null;
    }

    public String getDateBefore(String date) throws ParseException {
        Date realDate = simpleDateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(realDate);
        calendar.add(Calendar.DATE, -1);
        return simpleDateFormat.format(calendar.getTime());
    }
}
