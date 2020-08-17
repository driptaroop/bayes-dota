package gg.bayes.challenge.util;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class CommonUtils {
    public static LocalTime parseTime(String s) {
        return LocalTime.parse(s.substring(1, 13));
    }
    public static long gameTimeInMillis(String time) {
        return parseTime(time).getLong(ChronoField.MILLI_OF_DAY);
    }
}
