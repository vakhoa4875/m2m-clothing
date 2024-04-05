package m2m_phase2.clothing.clothing.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static String toDateFormat(String dateInput, String inputPattern, String outputPattern) {
        // Định dạng đầu vào
        DateFormat inputFormat = new SimpleDateFormat(inputPattern);
        // Định dạng đầu ra
        DateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String formattedDate = null;
        try {
            // Chuyển đổi từ chuỗi sang đối tượng Date
            Date date = inputFormat.parse(dateInput);
            // Chuyển đổi từ đối tượng Date sang chuỗi theo định dạng mới
            formattedDate = outputFormat.format(date);
            System.out.println("Chuỗi đã định dạng lại: " + formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return inputDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
