package net.kdks.model.sf;

import lombok.Data;

/**
 * 顺丰轨迹查询结果.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ApiResultData {
    /**
     * 是否成功.
     */
    private Boolean success;
    /**
     * 错误码.
     */
    private String errorCode;
    /**
     * 错误信息.
     */
    private String errorMsg;
    /**
     * 查询信息.
     */
    private MsgData msgData;

}
