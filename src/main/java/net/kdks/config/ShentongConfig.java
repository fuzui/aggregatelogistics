package net.kdks.config;

/**
 * 申通快递配置.
 * 
 * <p>API配置,包含[appkey,secretKey],isProduct标识测试与生成<br>
 * 配置获取:<em><a href="https://open.sto.cn/">申通开放平台</a></em>
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShentongConfig {
	
	private ShentongConfig(Builder builder) {
		appkey = builder.appkey;
		secretKey = builder.secretKey;
		isProduct = builder.isProduct;
    }
	
	private String appkey;
	
	private String secretKey;
	
	private int isProduct;
	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getIsProduct() {
		return isProduct;
	}

	public void setIsProduct(int isProduct) {
		this.isProduct = isProduct;
	}

	public static Builder builder() {
        return new Builder();
    }
	
	public static class Builder {
		private String appkey;
		private String secretKey;
		private int isProduct = 1;
        public Builder appkey(String appkey) {
            this.appkey = appkey;
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }
        
        public Builder isProduct(int isProduct) {
            this.isProduct = isProduct;
            return this;
        }

        public ShentongConfig build() {
            return new ShentongConfig(this);
        }
    }

	@Override
	public String toString() {
		return "ShentongConfig [appkey=" + appkey + ", secretKey=" + secretKey + ", isProduct=" + isProduct + "]";
	}
	
}
