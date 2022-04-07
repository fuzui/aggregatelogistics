package net.kdks.utils;

/**
 * 断言.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
public class Assert {
    /**
     * 判断是否为null.
     *
     * @param object 判断的对象
     * @param message 错误消息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断字符串是否为空.
     *
     * @param str 判断的对象
     * @param message 错误消息
     */
    public static void notEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(message);
        }
    }
}
