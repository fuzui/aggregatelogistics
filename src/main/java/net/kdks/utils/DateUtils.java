package net.kdks.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * 日期时间处理.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class DateUtils {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前日期
	 * @return	当前日期
	 */
	public static String currentTimeStr() {
		return currentTimeStr(DEFAULT_FORMAT);
	}
	
	/**
	 * 获取指定格式的日期
	 * 
	 * @param format	日期格式
	 * @return	当前日期
	 */
	public static String currentTimeStr(String format) {
		if(format == null || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		LocalDateTime dateTime=LocalDateTime.now();
		String result = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
		result = dateTime.format(DateTimeFormatter.ofPattern(format));
		return result;
	}
	
	/**
	 * 获取当前时间戳(毫秒)
	 * 
	 * @return 当前时间戳(毫秒)
	 */
	public static long currentTimeMillis() {
		return currentTimes().getLong(ChronoField.INSTANT_SECONDS)*1000
				+currentTimes().getLong(ChronoField.MILLI_OF_SECOND);
	}
	
	/**
	 * 获取当前时间戳(秒)
	 * 
	 * @return 当前时间戳(秒)
	 */
	public static long currentTimeSeconds() {
		return currentTimes().getLong(ChronoField.INSTANT_SECONDS);
	}
	
	/**
	 * 字符串时间转时间戳
	 * 
	 * @return 当前时间戳(秒)
	 */
	public static long strToTimestamp(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        return timestamp;
    }
	
	/**
	 * 获取当前日期(+8)
	 * @return 当前日期(+8)
	 */
	private static OffsetDateTime currentTimes() {
		Instant instant=Instant.now();
		OffsetDateTime offsetDateTime =instant.atOffset(ZoneOffset.ofHours(8));
		return offsetDateTime;
	}
}
