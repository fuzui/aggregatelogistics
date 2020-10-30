package net.kdks.config;

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

	public ShunfengConfig getShunfengConfig() {
		return shunfengConfig;
	}

	public void setShunfengConfig(ShunfengConfig shunfengConfig) {
		this.shunfengConfig = shunfengConfig;
	}
	
	public ShentongConfig getShentongConfig() {
		return shentongConfig;
	}

	public void setShentongConfig(ShentongConfig shentongConfig) {
		this.shentongConfig = shentongConfig;
	}
	
	public BaishiConfig getBaishiConfig() {
		return baishiConfig;
	}

	public void setBaishiConfig(BaishiConfig baishiConfig) {
		this.baishiConfig = baishiConfig;
	}
	
	public YuantongConfig getYuantongConfig() {
		return yuantongConfig;
	}

	public void setYuantongConfig(YuantongConfig yuantongConfig) {
		this.yuantongConfig = yuantongConfig;
	}
	
	public ZhongtongConfig getZhongtongConfig() {
		return zhongtongConfig;
	}

	public void setZhongtongConfig(ZhongtongConfig zhongtongConfig) {
		this.zhongtongConfig = zhongtongConfig;
	}

	public static Builder builder() {
        return new Builder();
    }
	
	public static class Builder {
		private ShunfengConfig shunfengConfig;
		
		private ShentongConfig shentongConfig;
		
		private BaishiConfig baishiConfig;
		
		private YuantongConfig yuantongConfig;
		
		private ZhongtongConfig zhongtongConfig;
		
		/**
		 * 顺丰
		 * @param partnerID
		 * @param requestID
		 * @param checkWord
		 * @return
		 */
        public Builder shunfengConfig(String partnerID,String requestID,String checkWord) {
            this.shunfengConfig = ShunfengConfig.builder()
            		.partnerID(partnerID)
            		.requestID(requestID)
            		.checkWord(checkWord)
            		.build();
            return this;
        }
        public Builder shunfengConfig(String partnerID,String requestID,String checkWord,int isProduct) {
            this.shunfengConfig = ShunfengConfig.builder()
            		.partnerID(partnerID)
            		.requestID(requestID)
            		.checkWord(checkWord)
            		.isProduct(isProduct)
            		.build();
            return this;
        }
        
        /**
         * 申通
         * @param appkey
         * @param secretKey
         * @return
         */
        public Builder shentongConfig(String appkey, String secretKey) {
        	this.shentongConfig = ShentongConfig.builder()
        			.appkey(appkey)
        			.secretKey(secretKey)
        			.build();
        	return this;
        }
        public Builder shentongConfig(String appkey, String secretKey,int isProduct) {
        	this.shentongConfig = ShentongConfig.builder()
        			.appkey(appkey)
        			.secretKey(secretKey)
        			.isProduct(isProduct)
        			.build();
        	return this;
        }
        
        /**
         * 百世
         * @param partnerID
         * @param secretKey
         * @return
         */
        public Builder baishiConfig(String partnerID, String secretKey) {
        	this.baishiConfig = BaishiConfig.builder()
        			.partnerID(partnerID)
        			.secretKey(secretKey)
        			.build();
        	return this;
        }
        public Builder baishiConfig(String partnerID, String secretKey,int isProduct) {
        	this.baishiConfig = BaishiConfig.builder()
        			.partnerID(partnerID)
        			.secretKey(secretKey)
        			.isProduct(isProduct)
        			.build();
        	return this;
        }
        
        /**
         * 圆通
         * @param appkey
         * @param secretKey
         * @param userId
         * @return
         */
        public Builder yuantongConfig(String appkey, String secretKey, String userId) {
        	this.yuantongConfig = YuantongConfig.builder()
        			.appkey(appkey)
        			.secretKey(secretKey)
        			.userId(userId)
        			.build();
        	return this;
        }
        public Builder yuantongConfig(String appkey, String secretKey, String userId, int isProduct) {
        	this.yuantongConfig = YuantongConfig.builder()
        			.appkey(appkey)
        			.secretKey(secretKey)
        			.userId(userId)
        			.isProduct(isProduct)
        			.build();
        	return this;
        }
        
        /**
         * 中通
         * @param appkey
         * @param secretKey
         * @return
         */
        public Builder zhongtongConfig(String companyId, String secretKey) {
        	this.zhongtongConfig = ZhongtongConfig.builder()
        			.companyId(companyId)
        			.secretKey(secretKey)
        			.build();
        	return this;
        }
        public Builder zhongtongConfig(String companyId, String secretKey,int isProduct) {
        	this.zhongtongConfig = ZhongtongConfig.builder()
        			.companyId(companyId)
        			.secretKey(secretKey)
        			.isProduct(isProduct)
        			.build();
        	return this;
        }
        
        public ExpressConfig build() {
            return new ExpressConfig(this);
        }
    }
	
	
}
