package net.kdks.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * 日期时间处理.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class DateUtils {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期.
     *
     * @return 当前日期
     */
    public static String currentTimeStr() {
        return currentTimeStr(DEFAULT_FORMAT);
    }

    /**
     * 获取指定格式的日期.
     *
     * @param format 日期格式
     * @return 当前日期
     */
    public static String currentTimeStr(String format) {
        if (format == null || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前时间戳(毫秒).
     *
     * @return 当前时间戳(毫秒)
     */
    public static long currentTimeMillis() {
        return currentTimes().getLong(ChronoField.INSTANT_SECONDS) * 1000
            + currentTimes().getLong(ChronoField.MILLI_OF_SECOND);
    }

    /**
     * 获取当前时间戳(秒).
     *
     * @return 当前时间戳(秒)
     */
    public static long currentTimeSeconds() {
        return currentTimes().getLong(ChronoField.INSTANT_SECONDS);
    }

    public static long millisToSeconds(Long millis) {
        return millis / 1000;
    }

    /**
     * 字符串时间转时间戳.
     *
     * @return 当前时间戳(秒)
     */
    public static long strToTimestamp(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }

    /**
     * 毫秒转日期字符串.
     *
     * @param millSec 毫秒
     * @param dateFormat 格式
     * @return 日期字符串
     */
    public static String millLongToDateStr(Long millSec, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 毫秒转日期字符串.
     *
     * @param millSec 毫秒
     * @return 日期字符串
     */
    public static String millLongToDateStr(Long millSec) {
        return millLongToDateStr(millSec, DEFAULT_FORMAT);
    }

    /**
     * 秒转日期字符串.
     *
     * @param sec 秒
     * @param dateFormat 格式
     * @return 日期字符串
     */
    public static String secondLongToDateStr(Long sec, String dateFormat) {
        return millLongToDateStr(sec * 1000L, dateFormat);
    }

    /**
     * 秒转日期字符串.
     *
     * @param sec 秒
     * @return 日期字符串
     */
    public static String secondLongToDateStr(Long sec) {
        return secondLongToDateStr(sec, DEFAULT_FORMAT);
    }

    /**
     * 获取当前日期(+8).
     *
     * @return 当前日期(+ 8)
     */
    private static OffsetDateTime currentTimes() {
        Instant instant = Instant.now();
        return instant.atOffset(ZoneOffset.ofHours(8));
    }
}
