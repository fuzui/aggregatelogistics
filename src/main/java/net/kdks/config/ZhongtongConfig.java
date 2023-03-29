package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中通快递配置.
 *
 * <p>API配置,包含[appKey,secretKey],isProduct标识测试与生产环境<br>
 * <p>若需使用v1版本的路由查询，需填写companyId与secretKeyV1，并将routeVersion设置为v1</p>
 * 配置获取:<em><a href="https://zop.zto.com/">中通开放平台</a></em>
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhongtongConfig {

    private String companyId;

    private String secretKeyV1;

    private String appKey;

    private String secretKey;

    /**
     * 路由查询版本(v1/v2).
     */
    private String routeVersion;

    private int isProduct = 1;

}
