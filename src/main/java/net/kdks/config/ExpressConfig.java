package net.kdks.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聚合快递配置.
 *
 * <p>包含顺丰、申通、百世、圆通、中通.
 * <pre>
 * ExpressConfig config = ExpressConfig.builder()
 * .shunfengConfig("partnerId","requestId","checkWord",1)
 * .shentongConfig("appkey", "secretKey", 1)
 * .baishiConfig("partnerId", "secretKey", 1)
 * .yuantongConfig("appkey", "secretKey", "userId")
 * .zhongtongConfig("companyId", "secretKey", 0)
 * .build();
 * ExpressHandlers expressHandlers = new ExpressHandlers(config);
 * </pre>
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class ExpressConfig {

    private ExpressConfig(Builder builder) {
        shunfengConfig = builder.shunfengConfig;
        shentongConfig = builder.shentongConfig;
        baishiConfig = builder.baishiConfig;
        yuantongConfig = builder.yuantongConfig;
        zhongtongConfig = builder.zhongtongConfig;
    }

    private ShunfengConfig shunfengConfig;

    private ShentongConfig shentongConfig;

    private BaishiConfig baishiConfig;

    private YuantongConfig yuantongConfig;

    private ZhongtongConfig zhongtongConfig;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder.
     */
    public static class Builder {
        private ShunfengConfig shunfengConfig;

        private ShentongConfig shentongConfig;

        private BaishiConfig baishiConfig;

        private YuantongConfig yuantongConfig;

        private ZhongtongConfig zhongtongConfig;

        /**
         * 顺丰.
         *
         * @param partnerId partnerId
         * @param requestId requestId
         * @param checkWord checkWord
         * @return Builder
         */
        public Builder shunfengConfig(String partnerId, String requestId, String checkWord) {
            this.shunfengConfig =
                ShunfengConfig.builder().partnerId(partnerId).requestId(requestId)
                    .checkWord(checkWord)
                    .build();
            return this;
        }

        /**
         * 顺丰.
         *
         * @param partnerId partnerId
         * @param requestId requestId
         * @param isProduct isProduct
         * @return Builder
         */
        public Builder shunfengConfig(String partnerId, String requestId, String checkWord,
                                      int isProduct) {
            this.shunfengConfig =
                ShunfengConfig.builder().partnerId(partnerId).requestId(requestId)
                    .checkWord(checkWord)
                    .isProduct(isProduct).build();
            return this;
        }

        /**
         * 申通.
         *
         * @param appkey    appkey
         * @param secretKey secretKey
         * @return Builder
         */
        public Builder shentongConfig(String appkey, String secretKey) {
            this.shentongConfig =
                ShentongConfig.builder().appkey(appkey).secretKey(secretKey).build();
            return this;
        }

        /**
         * 申通.
         *
         * @param appkey    appkey
         * @param secretKey secretKey
         * @param isProduct isProduct
         * @return Builder
         */
        public Builder shentongConfig(String appkey, String secretKey, int isProduct) {
            this.shentongConfig =
                ShentongConfig.builder().appkey(appkey).secretKey(secretKey).isProduct(isProduct)
                    .build();
            return this;
        }

        /**
         * 百世.
         *
         * @param partnerId partnerId
         * @param secretKey secretKey
         * @return Builder
         */
        public Builder baishiConfig(String partnerId, String secretKey) {
            this.baishiConfig =
                BaishiConfig.builder().partnerId(partnerId).secretKey(secretKey).build();
            return this;
        }

        /**
         * 百世.
         *
         * @param partnerId partnerId
         * @param secretKey secretKey
         * @param isProduct isProduct
         * @return Builder
         */
        public Builder baishiConfig(String partnerId, String secretKey, int isProduct) {
            this.baishiConfig =
                BaishiConfig.builder().partnerId(partnerId).secretKey(secretKey)
                    .isProduct(isProduct)
                    .build();
            return this;
        }

        /**
         * 圆通.
         *
         * @param appkey    appkey
         * @param secretKey secretKey
         * @param userId    userId
         * @return Builder
         */
        public Builder yuantongConfig(String appkey, String secretKey, String userId) {
            this.yuantongConfig =
                YuantongConfig.builder().appkey(appkey).secretKey(secretKey).userId(userId).build();
            return this;
        }

        /**
         * 圆通.
         *
         * @param appkey    appkey
         * @param secretKey secretKey
         * @param userId    userId
         * @param isProduct isProduct
         * @return Builder
         */
        public Builder yuantongConfig(String appkey, String secretKey, String userId,
                                      int isProduct) {
            this.yuantongConfig =
                YuantongConfig.builder().appkey(appkey).secretKey(secretKey).userId(userId)
                    .isProduct(isProduct).build();
            return this;
        }

        /**
         * 中通.
         *
         * @param companyId companyId
         * @param secretKey secretKey
         * @return Builder
         */
        public Builder zhongtongConfig(String companyId, String secretKey) {
            this.zhongtongConfig =
                ZhongtongConfig.builder().companyId(companyId).secretKey(secretKey).build();
            return this;
        }

        /**
         * 中通.
         *
         * @param companyId companyId
         * @param secretKey secretKey
         * @param isProduct isProduct
         * @return Builder
         */
        public Builder zhongtongConfig(String companyId, String secretKey, int isProduct) {
            this.zhongtongConfig =
                ZhongtongConfig.builder().companyId(companyId).secretKey(secretKey)
                    .isProduct(isProduct)
                    .build();
            return this;
        }

        public ExpressConfig build() {
            return new ExpressConfig(this);
        }
    }


}
