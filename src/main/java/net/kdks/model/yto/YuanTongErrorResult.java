package net.kdks.model.yto;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * 圆通轨迹查询错误信息.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class YuanTongErrorResult {

    private Map<String, List> map;

    private String code;

    private String success;

    private String message;

    private String reason;

}
