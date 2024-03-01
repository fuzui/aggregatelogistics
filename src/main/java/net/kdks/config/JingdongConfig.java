package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 京东快递配置.
 *
 * <p>API配置,包含[appKey,appSecret,accessToken,customerCode],isProduct标识测试与生成<br>
 * 配置获取:<em><a href="https://cloud.jdl.com/">京东开放平台</a></em>
 *
 * @author Ze.Wang
 * @since 0.0.11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JingdongConfig {

    private String appKey;
    private String appSecret;
    private String accessToken;
    /**
     * 月结号.
     */
    private String customerCode;

    private int isProduct = 1;

}
