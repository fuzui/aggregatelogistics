package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 韵达快递配置.
 *
 * <p>API配置,包含[appKey,appSecret],isProduct标识测试与生产环境<br>
 * 配置获取:<em><a href="http://qiao.sf-express.com/">顺丰开放平台</a></em>
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YundaConfig {

    private String appKey;
    private String appSecret;

    private int isProduct = 1;

}
