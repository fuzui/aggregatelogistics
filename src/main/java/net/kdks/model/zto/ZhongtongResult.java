package net.kdks.model.zto;

import lombok.Data;

/**
 * 中通轨迹查询结果.
 *
 * @author Ze.Wang
 * @since 1.0.0
 */
@Data
public class ZhongtongResult<T> {

    /**
     * 数据.
     */
    private T result;

    /**
     * 是否成功.
     */
    private Boolean status;
    /**
     * 错误信息.
     */
    private String message;

    private String statusCode;

}
