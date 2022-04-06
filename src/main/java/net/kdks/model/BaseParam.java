package net.kdks.model;

import java.io.Serializable;
import lombok.Data;
import net.kdks.constant.RequestConstant;

/**
 * 物流查询入参.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String format = RequestConstant.JSON;

    /**
     * 快递公司官方简称.
     */
    private String expressCompanyNo;

}