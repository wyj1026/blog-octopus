package cn.tegongdete.converter;

import cn.tegongdete.enums.BlogPlatform;

import java.sql.Timestamp;

public class DateConverter {
    private static final long OneDayMillis = 86400000;
    public static Timestamp convert(String raw, BlogPlatform platform) {
        return new Timestamp(System.currentTimeMillis());
        /*
        Timestamp result;
        if (mightBeDaysBefore(platform) && isDaysBefore(raw)) {
            int daysBefore = Integer.valueOf(raw.substring(0, countNum(raw)));
            result = new Timestamp(System.currentTimeMillis() - daysBefore * OneDayMillis);
        }
        else {
            int year, month, date, hour, minute, second;
            String pure = getPureInt(raw);
            year = Integer.valueOf(pure.substring(0, 4)) - 1900;
            month = Integer.valueOf(pure.substring(4, 6)) - 1;
            date = Integer.valueOf(pure.substring(6, 8));
            hour = Integer.valueOf(pure.substring(8, 10));
            minute = Integer.valueOf(pure.substring(10, 12));
            second = Integer.valueOf(pure.substring(12, 14));
            result = new Timestamp(year, month, date, hour, minute, second, 0);
        }
        return result;
        */
    }

    private static boolean mightBeDaysBefore(BlogPlatform blogPlatform) {
        return blogPlatform == BlogPlatform.SEGMENTFAULT;
    }

    private static boolean isDaysBefore(String raw) {
        return countNum(raw) != 8;
    }

    private static int countNum(String raw) {
        int count = 0;
        for (char c: raw.toCharArray()) {
            if (c >= '0' && c <= '9') {
                count++;
            }
        }
        return count;
    }

    private static String getPureInt(String s) {
        char[] r = new char[14];
        int index = 0;
        for (char c: s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                r[index++] = c;
            }
        }
        return r.toString();
    }
}
