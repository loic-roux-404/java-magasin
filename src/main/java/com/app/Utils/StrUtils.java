package com.app.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtils {
    /**
     * Convert date to human readable string
     *
     * @param date date to format
     * @return string
     */
    public static String dateFmt(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(date);
    }

    /**
     * Cast Object to string and trim whitespaces
     *
     * @param o object to stringify
     * @return string
     */
    public static String updateString(Object o) throws Exception {
        return ((String) o).trim();
    }

    /**
     * Cast Object to integer and trim whitespaces
     *
     * @param o object to convert to int
     * @return int
     */
    public static int updateInteger(Object o) throws NumberFormatException {
        String str = ((String) o).trim();
        return Integer.parseInt(str);
    }

    /**
     * Cast Object to double and trim whitespaces
     *
     * @param o object to convert to double
     * @return double
     */
    public static double updateDouble(Object o) throws NumberFormatException {
        String str = ((String) o).trim();
        return Double.parseDouble(str);
    }
}
