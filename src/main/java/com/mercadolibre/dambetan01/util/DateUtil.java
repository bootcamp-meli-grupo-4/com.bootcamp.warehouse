package com.mercadolibre.dambetan01.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtil {

    public static LocalDateTime calendarToLocalDateTime(Calendar calendar){
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }


    public static LocalDate calendarToLocalDate(Calendar calendar){
        return LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }

    public static Calendar localDateToDate(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        return calendar;
    }

    public static Calendar localDateToDateTime(LocalDateTime localDateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        return calendar;
    }
}
