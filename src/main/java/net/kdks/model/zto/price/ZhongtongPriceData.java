package net.kdks.model.zto.price;

import lombok.Data;

/**
 * 中通运费估算数据.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
@Data
public class ZhongtongPriceData {

    /**
     * 运费.
     */
    private String price;
    /**
     * 时效.
     */
    private String hour;

}
