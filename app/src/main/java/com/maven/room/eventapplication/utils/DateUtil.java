/*
package com.maven.room.eventapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(Helper.DATE_FORMAT2);
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public Date getDateFromString(String stringDate) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(Helper.DATE_FORMAT2);
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addDays(Date date, int days) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }
        return null;
    }
}
*/
