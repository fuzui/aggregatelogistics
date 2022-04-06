package net.kdks.enums;


import java.util.stream.Stream;

/**
 * 枚举接口.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ValueEnum<T> {

    /**
     * 枚举转换.
     *
     * @param enumType 类型
     * @param value    值
     * @param <V>      通用值
     * @param <E>      通用枚举
     * @return corresponding enum
     */
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {

        return Stream.of(enumType.getEnumConstants())
            .filter(item -> item.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unknown value: " + value));
    }

    /**
     * 获取枚举值.
     *
     * @return enum value
     */
    T getValue();

}
