package top.yigumoyan.chat.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 计算两个 Date 之间差的天数
     *
     * @param date1 Date1
     * @param date2 Date2
     * @return 天数
     */
    public static int getDays(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        // 不同年
        if (year1 != year2)
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    // 闰年
                    timeDistance += 366;
                } else {
                    // 不是闰年
                    timeDistance += 365;
                }
            }
            return Math.abs(timeDistance + (day2 - day1));
        } else {
            // 同年
            return Math.abs(day2 - day1);
        }
    }
}
