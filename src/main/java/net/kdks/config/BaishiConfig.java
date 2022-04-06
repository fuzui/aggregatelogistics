package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 百世快递配置.
 *
 * <p>API配置,包含[partnerId,secretKey],isProduct标识测试与生成<br>
 * 配置获取:<em><a href="https://open.800best.com/">百世开放平台</a></em>
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaishiConfig {

    private String partnerId;
    private String secretKey;
    private int isProduct = 1;

}
