package net.kdks.model.yd;

import lombok.Data;

/**
 * 中通轨迹查询结果.
 *
 * @author Ze.Wang
 * @since 1.0.0
 */
@Data
public class YundaResult<T> {

    /**
     * 数据.
     */
    private T data;

    /**
     * 是否成功.
     */
    private Boolean result;
    /**
     * 错误信息.
     */
    private String message;

    private String code;

}
