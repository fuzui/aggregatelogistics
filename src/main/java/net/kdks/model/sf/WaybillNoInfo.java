package net.kdks.model.sf;

import lombok.Data;

/**
 * 单号.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class WaybillNoInfo {
    /**
     * 运单号类型1：母单 2 :子单 3 : 签回单.
     */
    private Integer waybillType;

    /**
     * 运单号.
     */
    private String waybillNo;

}
