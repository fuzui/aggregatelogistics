package net.kdks.model.sto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 申通轨迹查询结果.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShentongResult extends ShentongBaseResult {
    /**
     * 是否重试.
     */
    private Boolean needRetry;
    /**
     * 请求id.
     */
    private String requestId;
    /**
     * 异常信息.
     */
    private String expInfo;
    /**
     * 查询结果Map,key是查询的单号，value是对应的轨迹集合.
     */
    private Map<String, List<ShentongRoute>> data;

}
