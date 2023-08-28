package net.kdks.model;

import java.io.Serializable;
import lombok.Data;


/**
 * 订阅结果.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ExpressRouteSubscribeData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订阅单号.
     */
    private String expressNo;

    /**
     * 订阅结果.
     */
    private Boolean result;

}
