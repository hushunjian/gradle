package com.hushunjian.gradle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date stringToDate(String val, String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.parse(val);
        } catch (ParseException e) {
            logger.warn("{} parse Date error", val);
        }
        return null;
    }
}
