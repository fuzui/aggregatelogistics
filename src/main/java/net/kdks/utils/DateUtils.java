package net.kdks.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateUtils {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String currentTimeStr() {
		return currentTimeStr(DEFAULT_FORMAT);
	}
	
	public static String currentTimeStr(String format) {
		if(format == null || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		LocalDateTime dateTime=LocalDateTime.now();
		String result = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
		result = dateTime.format(DateTimeFormatter.ofPattern(format));
		return result;
	}
	
	public static long currentTimeMillis() {
		return currentTimes().getLong(ChronoField.INSTANT_SECONDS)*1000
				+currentTimes().getLong(ChronoField.MILLI_OF_SECOND);
	}
	
	public static long currentTimeSeconds() {
		return currentTimes().getLong(ChronoField.INSTANT_SECONDS);
	}
	public static long strToTimestamp(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        return timestamp;
    }
	
	private static OffsetDateTime currentTimes() {
		Instant instant=Instant.now();
		OffsetDateTime offsetDateTime =instant.atOffset(ZoneOffset.ofHours(8));
		return offsetDateTime;
	}
}
