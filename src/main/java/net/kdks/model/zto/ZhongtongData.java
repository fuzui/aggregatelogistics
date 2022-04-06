package net.kdks.model.zto;

import java.util.List;
import lombok.Data;

/**
 * 中通轨迹查询数据.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ZhongtongData {
    /**
     * 路由信息.
     */
    private List<ZhongtongTrace> traces;
    /**
     * 快递号.
     */
    private String billCode;

}
