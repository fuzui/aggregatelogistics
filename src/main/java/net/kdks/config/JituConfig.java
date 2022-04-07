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
public class JituConfig {

    private String apiAccount;
    private String privateKey;

    /**
     * 沙箱环境uuid.
     */
    private String uuid;
    /**
     * 客户账号(选填),以下需要必填:
     * 运费查询、下单.
     */
    private String customerCode;
    /**
     * 客户密码(选填),以下需要必填:
     * 运费查询、下单.
     */
    private String customerPwd;

    private int isProduct = 1;

}
