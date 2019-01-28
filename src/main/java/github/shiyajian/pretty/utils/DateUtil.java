package github.shiyajian.pretty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shiyajian
 * create: 2018-12-06
 */
public final class DateUtil {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String TIME_PATTERN = "HH:mm:ss";

    private static final String OPEN_SUFFIX = "00:00:00";

    private static final String CLOSE_SUFFIX = "23:59:59";

    private static final String SEPARATOR = " ";

    private DateUtil() { /* no instance */ }

    public static Date openOf(Date date) {
        try {
            return new SimpleDateFormat(DATETIME_PATTERN).parse(open(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date closeOf(Date date) {
        try {
            return new SimpleDateFormat(DATETIME_PATTERN).parse(close(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String open(Date date) {
        String dateAfterFormat = new SimpleDateFormat(DATE_PATTERN).format(date);
        return (dateAfterFormat + SEPARATOR + OPEN_SUFFIX);
    }

    public static String close(Date date) {
        String dateAfterFormat = new SimpleDateFormat(DATE_PATTERN).format(date);
        return (dateAfterFormat + SEPARATOR + CLOSE_SUFFIX);
    }

    public static class Now {

        private Now() { /* no instance */ }

        public static String get() {
            return new SimpleDateFormat(DATETIME_PATTERN).format(new Date());
        }

    }

    public static class Today {

        private Today() { /* no instance */ }

        public static String open() {
            return DateUtil.open(new Date());
        }

        public static String close() {
            return DateUtil.close(new Date());
        }
    }

}
