package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 圆通快递配置.
 *
 * <p>API配置,包含[partnerId,secretKey,userId],isProduct标识测试与生产环境<br>
 * 配置获取:<em><a href="http://open.yto.net.cn/">圆通开放平台</a></em>
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YuantongConfig {

    private String appkey;

    private String secretKey;

    private String userId;

    private int isProduct = 1;

}
