package net.kdks.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 下单结果.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class OrderResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号.
     */
    private String orderId;
    /**
     * 运单号.
     */
    private String expressNo;

}
