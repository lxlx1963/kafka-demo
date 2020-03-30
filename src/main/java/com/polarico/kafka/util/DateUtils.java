package com.polarico.kafka.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

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

}
