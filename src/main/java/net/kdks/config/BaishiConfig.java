package net.kdks.config;

/**
 * 百世快递配置.
 * 
 * <p>API配置,包含[partnerId,secretKey],isProduct标识测试与生成<br>
 * 配置获取:<em><a href="https://open.800best.com/">百世开放平台</a></em>
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class BaishiConfig {
	
	private BaishiConfig(Builder builder) {
		partnerId = builder.partnerId;
		secretKey = builder.secretKey;
		isProduct = builder.isProduct;
    }
	
	private String partnerId;
	private String secretKey;
	private int isProduct;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
	
	public BaishiConfig() {
		super();
	}
	public BaishiConfig(String partnerId, String secretKey) {
		super();
		this.partnerId = partnerId;
		this.secretKey = secretKey;
	}
	
	public BaishiConfig(String partnerId, String secretKey, int isProduct) {
		super();
		this.partnerId = partnerId;
		this.secretKey = secretKey;
		this.isProduct = isProduct;
	}
	
	public static class Builder {
		private String partnerId;
		private String secretKey;
		private int isProduct = 1;
        public Builder partnerId(String partnerId) {
            this.partnerId = partnerId;
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

        public BaishiConfig build() {
            return new BaishiConfig(this);
        }
    }
	
	@Override
	public String toString() {
		return "ShunfengConfig [partnerId=" + partnerId + ", secretKey=" + secretKey + ", isProduct=" + isProduct + "]";
	}
	
	
	
	
}
