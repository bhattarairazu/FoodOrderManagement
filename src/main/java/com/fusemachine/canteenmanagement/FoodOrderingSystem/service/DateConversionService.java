package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateConversionService {

    public Date getTodaysdate(String format){
        String stringdatesformats = LocalDate.now()+format;

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateformat = null;
        // Date datesTo = null;
        try {
            dateformat = pattern.parse(stringdatesformats);
            //datesTo = pattern.parse(dateTo);
            System.out.print(dateformat + " ");
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dateformat;
    }

    public Date getyesterdayDate(String format) {
        // String dateFrom = LocalDate.now() + " 00:00:00.0";
        //String dateTo = LocalDate.now() + " 23:99:99.0";
        String stringdatesformats =getYesterdayDateString()+format;

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateformat = null;
        // Date datesTo = null;
        try {
            dateformat = pattern.parse(stringdatesformats);
            //datesTo = pattern.parse(dateTo);
            System.out.print(dateformat + " ");
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dateformat;
    }



    private static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(yesterday());
    }
    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
