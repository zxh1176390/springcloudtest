package com.zxh.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:53
 */
public class DateUtil {
    private static String defaultFomat = "yyyy-MM-dd HH:mm:ss";
    public static String ymdFmt = "yyyy-MM-dd";

    public DateUtil() {
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getFullTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日 HH时mm分ss秒");
        return sdf.format(date);
    }

    public static String getCurrentFullTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSSS");
        return sdf.format(getCurrentDate());
    }

    public static String getCurrentFormatTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultFomat);
        return sdf.format(getCurrentDate());
    }

    public static String getCurrentFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(ymdFmt);
        return sdf.format(getCurrentDate());
    }

    public static Date getDateByStr(String strTime, String... format) throws ParseException {
        if (StringUtils.isEmpty(strTime)) {
            return null;
        } else {
            String fmt = defaultFomat;
            if (format != null && format.length > 0) {
                fmt = format[0];
            }

            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.parse(strTime);
        }
    }

    public static String getDateByStr(Date date, String... format) {
        if (date == null) {
            return null;
        } else {
            String fmt = defaultFomat;
            if (format != null && format.length > 0) {
                fmt = format[0];
            }

            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.format(date);
        }
    }

    public static long getTwoStrDateDiff(String startDate, String endDate) throws ParseException {
        Date startTime = getDateByStr(startDate, ymdFmt);
        Date endTime = getDateByStr(endDate, ymdFmt);
        return getTwoDateDiff(startTime, endTime);
    }

    public static long getTwoStrDatePreciseDiff(String startDate, String endDate) throws ParseException {
        Date startTime = getDateByStr(startDate, defaultFomat);
        Date endTime = getDateByStr(endDate, defaultFomat);
        return getTwoDateDiff(startTime, endTime);
    }

    public static long getTwoStrDateDiffHour(String startDate, String endDate) throws ParseException {
        Date startTime = getDateByStr(startDate, defaultFomat);
        Date endTime = getDateByStr(endDate, defaultFomat);
        return getTwoDateDiffHour(startTime, endTime);
    }

    public static long getTwoStrDateDiffSec(String startDate, String endDate) throws ParseException {
        Date startTime = getDateByStr(startDate, defaultFomat);
        Date endTime = getDateByStr(endDate, defaultFomat);
        return getTwoDateDiffSec(startTime, endTime);
    }

    public static long getTwoDateDiffHour(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        return diff / 1000L / 60L / 60L;
    }

    public static long getTwoDateDiffSec(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        return diff / 1000L;
    }

    public static long getTwoDateDiff(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        return diff / 1000L / 60L / 60L / 24L;
    }

    public static Date countDate(Date time, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(10, day * 24);
        return cal.getTime();
    }

    public static Date countDateByHour(Date time, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(10, hour);
        return cal.getTime();
    }

    public static Date countDateByMinutes(Date time, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(12, minute);
        return cal.getTime();
    }

    public static String countDate(String time, int day) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateByStr(time, ymdFmt));
        cal.add(5, day);
        return getDateByStr(cal.getTime(), ymdFmt);
    }

    public static String getFristOfWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(7) == 1) {
            cal.add(5, -1);
        }

        cal.set(7, 2);
        Date mondayOld = cal.getTime();
        return getDateByStr(mondayOld, ymdFmt);
    }

    public static String getLastOfWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(7) == 1) {
            cal.add(5, -1);
        }

        cal.set(7, 7);
        cal.setTimeInMillis(cal.getTimeInMillis() + 86400000L);
        Date sundayOld = cal.getTime();
        return getDateByStr(sundayOld, ymdFmt);
    }

    public static String getLastDayOfMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);
        int month = Integer.parseInt(yearMonth.split("-")[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        int lastDay = cal.getActualMaximum(5);
        cal.set(5, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String getLargeTime(String... date) throws ParseException {
        Calendar largeDate = null;
        Calendar tmp = Calendar.getInstance();
        String[] var3 = date;
        int var4 = date.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String str = var3[var5];
            if (largeDate == null) {
                largeDate = Calendar.getInstance();
                largeDate.setTime(getDateByStr(str));
            } else {
                tmp.setTime(getDateByStr(str));
                if (largeDate.before(tmp)) {
                    largeDate = tmp;
                }
            }
        }

        return getDateByStr(largeDate.getTime());
    }

    public static String getLastMonth(String date) {
        YearMonth yearMonth = YearMonth.parse(date);
        return yearMonth.minus(1L, ChronoUnit.MONTHS).toString();
    }

    public static String getLastYear(String date) {
        YearMonth yearMonth = YearMonth.parse(date);
        return yearMonth.minus(1L, ChronoUnit.YEARS).toString();
    }

    public static Date getCurrentQuarterStartTime() {
        SimpleDateFormat defsmf = new SimpleDateFormat(defaultFomat);
        SimpleDateFormat smf = new SimpleDateFormat(ymdFmt);
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(2) + 1;
        Date now = null;

        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(2, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(2, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(2, 4);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(2, 9);
            }

            c.set(5, 1);
            now = defsmf.parse(smf.format(c.getTime()) + " 00:00:00");
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return now;
    }

    public static Date getCurrentQuarterEndTime() {
        SimpleDateFormat defsmf = new SimpleDateFormat(defaultFomat);
        SimpleDateFormat smf = new SimpleDateFormat(ymdFmt);
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(2) + 1;
        Date now = null;

        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(2, 2);
                c.set(5, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(2, 5);
                c.set(5, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(2, 8);
                c.set(5, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(2, 11);
                c.set(5, 31);
            }

            now = defsmf.parse(smf.format(c.getTime()) + " 23:59:59");
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return now;
    }

    public static void main(String[] args) throws Exception {
        Date originEndTime = getDateByStr("2019-10-11 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Date date = countDate((Date)originEndTime, 1);
        System.out.println(date);
    }

    public static String getAFewDaysAgo(Integer day) {
        if (day == null) {
            day = 7;
        }

        Date dt = new Date();
        long time = dt.getTime();
        long sevenTime = (long)(day * 24 * 60 * 60 * 1000);
        long times = time - sevenTime;
        Date dat = new Date(times);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sb = format.format(gc.getTime());
        System.out.println(sb);
        return sb;
    }

    public static long getDiffSecondDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, 1);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        Long timeOut = (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000L;
        return timeOut;
    }
}

