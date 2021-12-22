package com.example.news.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class Utils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static String getDateToday() {
        Date date_today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date_today);
    }

    public static String getDateString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.valueOf(LocalDateTime.parse(String.valueOf(localDateTime), formatter));
    }

    public static LocalDateTime getLocalDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.parse(date, formatter);
    }

    public static String getDateString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.valueOf(LocalDateTime.parse(String.valueOf(timestamp), formatter));
    }

    public static Timestamp getTimestampFromString(String date) {
        Date dateFormatted = null;
//        try {
//            dateFormatted = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm z").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return Timestamp.valueOf(String.valueOf(dateFormatted));

        //        String pattern = "MMM dd, yyyy HH:mm:ss.SSSSSSSS";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
//        return Timestamp.valueOf(localDateTime);

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");
//        try {
//            dateFormatted = format.parse(date.substring(0,23));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return Timestamp.valueOf(String.valueOf(dateFormatted));
        String dateClean = date.substring(0,10)+" "+date.substring(11,19);
//        System.out.println(LocalDateTime.from(dateClean));
//        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
//        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        try {
//            Date theDate = utcFormat.parse();
//            DateFormat intendedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            intendedFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
//            return Timestamp.valueOf(intendedFormat.format(theDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
        return Timestamp.valueOf(dateClean);
    }

}
