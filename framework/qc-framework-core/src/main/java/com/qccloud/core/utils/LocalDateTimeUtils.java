package com.qccloud.core.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @Author: 青菜
 * @Date: 2019-06-14 17:36
 * @Description: LocalDateTimeUtils
 * @Version 1.0
 */
public class LocalDateTimeUtils {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
    public static final DateTimeFormatter DATETIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private static final ZoneId ZONE = ZoneId.of("Asia/Shanghai");


    /**
     * 获取当前系统时间
     * @return
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前系统日期
     * @return
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前系统日期时间
     * @return
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 字符串转LocalTime
     * @param time
     * @return
     */
    public static LocalTime str2LocalTime(String time) {
        return LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * 字符串转LocalDate
     * @param date
     * @return
     */
    public static LocalDate str2LocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * 字符串转LocalDateTime
     * @param dateTime
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }



    /**
     * 获取指定日期 几天前 几天前 或者几天后  最小时间
     * @param date
     * @param days
     * @return
     */
    public static LocalDateTime getMinTime(LocalDateTime date, Integer days) {
        LocalDate localDate = getLocalDate(date, days);
        LocalDateTime minTime = LocalDateTime.of(localDate, LocalTime.MIN);

        return minTime;
    }

    /**
     * 获取指定日期 几天前 或者几天后 最大时间
     * @param date
     * @param days 正数加天数 负数减天数
     * @return
     */
    public static LocalDateTime getMaxTime(LocalDateTime date, Integer days) {
        LocalDate localDate = getLocalDate(date, days);

        LocalDateTime maxTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return maxTime;
    }

    private static LocalDate getLocalDate(LocalDateTime date, Integer days) {
        LocalDate localDate = null;
        if(days == null || days == 0) {
            localDate = date.toLocalDate();
        }
        if(days > 0) {
            localDate = date.toLocalDate().plusDays(days);
        }
        if(days < 0) {
            localDate = date.toLocalDate().minusDays(Math.abs(days));
        }
        return localDate;
    }

    /**
     * 获取指定日期 最小时间
     * @param date
     * @return
     */
    public static LocalDateTime getMinTime(LocalDateTime date) {
        LocalDateTime minTime = LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
        return minTime;
    }

    /**
     * 获取指定日期 最大时间
     * @param date
     * @return
     */
    public static LocalDateTime getMaxTime(LocalDateTime date) {
        LocalDateTime maxTime = LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
        // maxTime.withNano(3);
        //  maxTime.minusSeconds(1);
        return maxTime;
    }

    /**
     * 获取当前日期几天前最小时间
     * @param days
     * @return
     */
    public static LocalDateTime getMinTime(Integer days) {
        return getMinTime(LocalDateTime.now(), days);
    }

    /**
     * 获取当前日期几天前最大时间
     * @param days
     * @return
     */
    public static LocalDateTime getMaxTime(Integer days) {
        return getMaxTime(LocalDateTime.now(), days);
    }


    /**
     * 获取某个时间是否在两个时间区间之内
     * @param startTime
     * @param endTime
     * @param time
     * @return 1-是 0-否
     */
    public static Integer getTimeIsBetween(LocalTime startTime, LocalTime endTime, LocalTime time) {

        int startHour = startTime.getHour();
        int startMinute = startTime.getMinute();
        int startSecond = startTime.getSecond();

        int endHour = endTime.getHour();
        int endMinute = endTime.getMinute();
        int endSecond = endTime.getSecond();

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();


        int beginTimeSecond = startHour * 60 * 60  + startMinute * 60 + startSecond;
        int endTimeSecond = endHour * 60 * 60  + endMinute * 60 + endSecond;
        int timeSecond = hour * 60 * 60  + minute * 60 + second;

        if(timeSecond >= beginTimeSecond && timeSecond <= endTimeSecond) {
            return 1;
        }
        return 0;
    }

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZONE);
    }


    /**
     * LocalDate转换为Date
     * @param time
     * @return
     */
    public static Date convertLDToDate(LocalDate time) {
        return Date.from(time.atStartOfDay(ZONE).toInstant());
    }


    /**
     *  Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZONE);
    }

    /**
     * LocalDateTime转换为Date
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZONE).toInstant());
    }


    /**
     * 获取指定日期的毫秒
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZONE).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZONE).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time,String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {return period.getYears();}
        if (field == ChronoUnit.MONTHS) {return period.getYears() * 12 + period.getMonths();}
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     * @param time
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
}
