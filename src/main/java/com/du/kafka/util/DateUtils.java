package com.du.kafka.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author dxy
 */
public class DateUtils {
    /**
     * 获取时间戳/秒
     *
     * @return Long
     */
    public static Long getTimeStampSecond() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取时间戳/毫秒
     *
     * @return Long
     */
    public static Long getTimeStampMilli() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取日期时间
     *
     * @param pattern String（格式）
     * @return String
     */
    public static String getDateTime(String pattern) {
        LocalDateTime now = LocalDateTime.now();
        if (StringUtils.isEmpty(pattern)) {
            now.toString();
        }
        return now.format(DateTimeFormatter.ofPattern(pattern));
    }
}
