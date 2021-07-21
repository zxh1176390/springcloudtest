package com.zxh.util;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:52
 */
public class DateUtils extends DateUtil {

    public static final DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter minuteDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter cnDateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    public static final DateTimeFormatter ymDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

    public static final DateTimeFormatter yDateFormatter = DateTimeFormatter.ofPattern("yyyy");

    private final long  SUM_SEC = 1000 * 60 * 60 * 24;

    private final long SEC_180 = 180* SUM_SEC;

    private final long SEC_30 = 30* SUM_SEC;


    /**
     * 获取当日剩余秒数
     * @return
     */
    public static long getNowDayRemainingSeconds(){
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
        return seconds;
    }


    /**
     * 获取两个时间的秒数
     * @param startTime
     * @param endTime
     * @return
     */
    public  static long duration(LocalDateTime startTime,LocalDateTime endTime){
        return  ChronoUnit.MILLIS.between(startTime,endTime);
    }


    /**
     *  在固定的日期时间上加秒数
     * @param dataTime
     * @param sec
     * @return
     */
    public static LocalDateTime addSec(LocalDateTime dataTime,long sec){
        return  dataTime.plusSeconds(sec);
    }

    /**
     * 获取两个时间的毫秒值
     * @return
     */
    public static long getTwoDiffMillis(LocalDateTime startTime,LocalDateTime endTime){
        Duration duration = Duration.between(startTime,endTime);
        return duration.toMillis();
    }


    /**
     * 在固定的日期时间上加几天
     * @param dateTime
     * @param day
     * @return
     */
    public static LocalDateTime addDays(LocalDateTime dateTime,int day){
        return  dateTime.plusDays(day);
    }

    /**
     * 在固定的日期时间上减几天
     * @param dateTime
     * @param day
     * @return
     */
    public static LocalDate minusDays(LocalDate dateTime,int day){
        return  dateTime.plusDays(day);
    }

    /**
     * 判断时间是不是在某个时间之前
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isAfter(String startTime,String endTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startTime.substring(0,10), formatter);
        LocalDate endDate = LocalDate.parse(endTime.substring(0,10), formatter);
        return startDate.isAfter(endDate);
    }

    /**
     * 判断时间是不是在某个时间之前
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBefore(String startTime,String endTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startTime.substring(0,10), formatter);
        LocalDate endDate = LocalDate.parse(endTime.substring(0,10), formatter);
        return startDate.isBefore(endDate);
    }

    /**
     * 判断时间是不是在当前时间之前
     * @param endTime
     * @return
     */
    public static boolean compare(LocalDateTime endTime){
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.isBefore(endTime);
    }

    public static int compareToTime(String startTime, String endTime) {
        return LocalDateTime.parse(startTime, defaultDateTimeFormatter).compareTo(LocalDateTime.parse(endTime, defaultDateTimeFormatter));
    }

    /**
     * 校验某一个日期时间是否在这两个之间
     * @param startTime
     * @param endTime
     * @param now
     * @return
     */
    public static boolean between(LocalDateTime startTime,LocalDateTime endTime,LocalDateTime now){
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    public static LocalDateTime parseDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, defaultDateTimeFormatter);
    }

    public static String formatDateTimeToCnDate(LocalDateTime localDateTime) {
        return localDateTime.format(cnDateFormatter);
    }

    public static LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static String formatDate(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    public static String formatDateTime(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) throws ParseException {
        /*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2021-01-10 17:07:05",df);
        LocalDateTime end = LocalDateTime.parse("2021-01-12 17:07:05",df);
       boolean is =  between(start,end,LocalDateTime.now());
        System.out.println(is);*/

       /* Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() +  1*24*60*60*1000);
        System.out.println(nowDate);
        System.out.println(expireDate);*/

        /*LocalDateTime start = LocalDateTime.parse("2020-12-01 10:00:00",defaultDateTimeFormatter);
        LocalDateTime addDays = DateUtils.addDays(start,2);
        long sec = DateUtils.duration(start,addDays);
        System.out.println(sec);

        System.out.println(compare(addDays));*/
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2020-11-20 10:00:00",df);
        LocalDateTime end = LocalDateTime.parse("2020-11-21 10:00:00",df);

        long sec = DateUtils.getTwoDiffMillis(start,end);
        System.out.println("sec:"+sec+"   SEC_30:"+1000 * 60 * 60 * 24);

        System.out.println(DateUtils.parseDate("2020-12-01", DateUtils.defaultDateFormatter));
        System.out.println(DateUtils.formatDate(DateUtils.parseDate("2020-12-01", DateUtils.defaultDateFormatter), DateUtils.ymDateFormatter));
        System.out.println(DateUtils.formatDate(LocalDate.now(), DateUtils.ymDateFormatter));
        System.out.println(DateUtils.formatDate(LocalDate.now(), DateUtils.yDateFormatter));
    }

}
