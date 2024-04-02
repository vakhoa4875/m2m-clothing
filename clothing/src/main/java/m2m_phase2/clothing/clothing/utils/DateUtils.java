package m2m_phase2.clothing.clothing.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return inputDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
