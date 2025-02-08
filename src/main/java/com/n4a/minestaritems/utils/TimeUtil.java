package com.n4a.minestaritems.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

@UtilityClass
public final class TimeUtil {
    public static String convertTime(long time, boolean withMills) {
        if (withMills) {
            long days = TimeUnit.MILLISECONDS.toDays(time);
            long hours = TimeUnit.MILLISECONDS.toHours(time) % 24;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
            long mills = TimeUnit.MILLISECONDS.toMillis(time) % 100;

            StringBuilder builder = new StringBuilder();
            if (days > 0) {
                builder.append(days).append("dni ").append(hours).append("godz ").append(minutes).append("min ").append(seconds).append("sek");
            } else if (hours > 0) {
                builder.append(hours).append("godz ").append(minutes).append("min ").append(seconds).append("sek");
            } else if (minutes > 0) {
                builder.append(minutes).append("min ").append(seconds).append("sek");
            } else if (seconds > 0) {
                builder.append(seconds).append("sek");
            } else {
                builder.append(mills).append("ms");
            }

            return builder.toString().trim();
        } else {
            if (time < 1000L) {
                return "< 1sek";
            }

            long days = TimeUnit.MILLISECONDS.toDays(time);
            long hours = TimeUnit.MILLISECONDS.toHours(time) % 24;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;

            StringBuilder builder = new StringBuilder();
            if (days > 0) {
                builder.append(days).append("dni ").append(hours).append("godz ").append(minutes).append("min ").append(seconds).append("sek");
            } else if (hours > 0) {
                builder.append(hours).append("godz ").append(minutes).append("min ").append(seconds).append("sek");
            } else if (minutes > 0) {
                builder.append(minutes).append("min ").append(seconds).append("sek");
            } else {
                builder.append(seconds).append("sek");
            }

            return builder.toString().trim();
        }
    }
}
