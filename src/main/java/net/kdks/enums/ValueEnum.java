package net.kdks.enums;


import java.util.stream.Stream;

/**
 * Interface for value enum.
 * 
 * @author: wangze
 * @date: 2020年9月19日 下午6:05:18
 */
public interface ValueEnum<T> {

    /**
     * Converts value to corresponding enum.
     *
     * @param enumType enum type
     * @param value    database value
     * @param <V>      value generic
     * @param <E>      enum generic
     * @return corresponding enum
     */
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {

        return Stream.of(enumType.getEnumConstants())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + value));
    }

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getValue();

}
