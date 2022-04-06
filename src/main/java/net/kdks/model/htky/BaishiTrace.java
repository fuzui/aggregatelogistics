package net.kdks.model.htky;

import java.util.List;
import lombok.Data;

/**
 * 流转信息.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class BaishiTrace {
    /**
     * 流转信息项.
     */
    private List<BaishiTraceItems> trace;

}
