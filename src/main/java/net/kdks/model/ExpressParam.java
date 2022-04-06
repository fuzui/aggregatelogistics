package net.kdks.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物流查询入参.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExpressParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 快递号
     */
    private List<String> expressNos;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 是否展示路由
     */
    private boolean isViewRoute = true;

    /**
     * 是否展示原生信息
     */
    private boolean isViewOriginal = false;

}