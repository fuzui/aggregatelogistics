package net.kdks.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中通快递配置.
 *
 * <p>API配置,包含[companyId,secretKey],isProduct标识测试与生产环境<br>
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

    private String secretKey;

    private int isProduct = 1;

}
