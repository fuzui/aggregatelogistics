package net.kdks.model.jt;

import lombok.Data;
/**
 * 极兔通用请求结果.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
@Data
public class JituResult<T> {
    /**
     * code.
     */
    private String code;
    /**
     * msg.
     */
    private String msg;
    /**
     * data.
     */
    private T data;

}
