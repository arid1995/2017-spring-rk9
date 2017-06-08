package ru.bmstu.rk9.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtils {
    public static String getCurrentTimestamp() {
        return "'" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
                .format(new Date(Instant.now().toEpochMilli()))) + "'";
    }
}
