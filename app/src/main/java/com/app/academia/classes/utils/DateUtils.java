package com.app.academia.classes.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DateUtils {

    public static Period getPeriodBetween(String startDate, String endDate, String pattern) {
        return Period.between(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern(pattern)),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern(pattern))
        );
    }

    public static int yearsDifference(String startDate, String endDate, String pattern) {
        return getPeriodBetween(startDate, endDate, pattern)
                .getYears();
    }

    public static int monthDifference(String startDate, String endDate, String pattern) {
        return getPeriodBetween(startDate, endDate, pattern)
                .getMonths();
    }

    public static int daysDifference(String startDate, String endDate, String pattern) {
        return getPeriodBetween(startDate, endDate, pattern)
                .getDays();
    }

    public static String getDate(int year, int month, int day, String pattern) {
        return LocalDate.of(year, month, day).format(DateTimeFormatter.ofPattern(pattern));
    }
}
