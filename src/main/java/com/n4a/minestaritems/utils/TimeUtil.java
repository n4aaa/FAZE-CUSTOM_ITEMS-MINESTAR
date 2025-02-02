package com.n4a.rng.utils;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public final class TimeUtil {

    static DecimalFormat df = new DecimalFormat("00");

    public static String getTime(int second) {
        int days = second / 86400;
        int hours = (second / 3600) % 24;
        int minutes = (second / 60) % 60;
        int seconds = second % 60;

        StringBuilder format = new StringBuilder();

        if (days > 0) {
            format.append(days).append("dni");
        }

        if (hours > 0) {
            if (format.length() > 0) {
                format.append(" ");
            }

            format.append(hours).append("h");
        }

        if (minutes > 0) {
            if (format.length() > 0) {
                format.append(" ");
            }

            format.append(minutes).append("min");
        }

        if (seconds > 0 || format.length() == 0) {
            if (format.length() > 0) {
                format.append(" ");
            }

            format.append(seconds).append("sek");
        }

        return format.toString();
    }

    public static String convertMills(long milliseconds) {
        long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60;
        long millis = milliseconds % 1000;

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append("dni ");
        }
        if (hours > 0) {
            sb.append(hours).append("godz ");
        }
        if (minutes > 0) {
            sb.append(minutes).append("min ");
        }
        if (seconds > 0) {
            sb.append(seconds).append("sek ");
        }
        if (millis > 0) {
            sb.append(millis).append("ms");
        }

        return sb.toString().trim();
    }

    public static String convertTime(long time) {
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

    public static String convertTimeWithMills(long time) {
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
    }

    public static String convertFloat(float value) {
        return String.format("%.1f", value);
    }

    public static long stringToTime(String x) {
        int number;
        x = x.toLowerCase();
        try {
            number = Integer.parseInt(x.substring(0, x.length() - 1));
        }
        catch (NumberFormatException e) {
            return -1L;
        }
        char type = x.charAt(x.length() - 1);
        switch (type) {
            case 'y': {
                return TimeUnit.DAYS.toMillis((long)number * 365L);
            }
            case 'w': {
                return TimeUnit.DAYS.toMillis((long)number * 7L);
            }
            case 'd': {
                return TimeUnit.DAYS.toMillis(number);
            }
            case 'h': {
                return TimeUnit.HOURS.toMillis(number);
            }
            case 'm': {
                return TimeUnit.MINUTES.toMillis(number);
            }
            case 's': {
                return TimeUnit.SECONDS.toMillis(number);
            }
        }
        return -1L;
    }

    public static String timeToString(long timeMillis) {
        if (timeMillis < 0) {
            return null;
        }

        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(timeMillis);
        long days = TimeUnit.MILLISECONDS.toDays(timeMillis);
        long weeks = days / 7;
        long years = days / 365;

        if (years > 0) {
            return years + "y";
        } else if (weeks > 0) {
            return weeks + "w";
        } else if (days > 0) {
            return days + "d";
        } else if (hours > 0) {
            return hours + "h";
        } else if (minutes > 0) {
            return minutes + "m";
        } else {
            return seconds + "s";
        }
    }

}
