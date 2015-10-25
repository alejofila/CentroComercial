package com.tesis.alejofila.centrocomercial.utils;

import java.util.GregorianCalendar;

/**
 * Created by Alejandro on 25/10/2015.
 */
public class DateUtils {

    public static String parseCalendar(GregorianCalendar gregorianCalendar){
        int year = gregorianCalendar.get(GregorianCalendar.YEAR);
        int month = gregorianCalendar.get(GregorianCalendar.MONTH)+1;
        int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        return year+"-"+month+"-"+day;

    }
}
