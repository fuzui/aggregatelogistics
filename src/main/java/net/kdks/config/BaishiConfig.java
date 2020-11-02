package net.kdks.config;

/**
 * 百世快递配置.
 * 
 * <p>API配置,包含[partnerID,secretKey],isProduct标识测试与生成<br>
 * 配置获取:<em><a href="https://open.800best.com/">百世开放平台</a></em>
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class BaishiConfig {
	
	private BaishiConfig(Builder builder) {
		partnerID = builder.partnerID;
		secretKey = builder.secretKey;
		isProduct = builder.isProduct;
    }
	
	private String partnerID;
	private String secretKey;
	private int isProduct;
	
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
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
	
	public BaishiConfig(String partnerID, String secretKey) {
		super();
		this.partnerID = partnerID;
		this.secretKey = secretKey;
	}
	
	public BaishiConfig(String partnerID, String secretKey, int isProduct) {
		super();
		this.partnerID = partnerID;
		this.secretKey = secretKey;
		this.isProduct = isProduct;
	}
	
	public static class Builder {
		private String partnerID;
		private String secretKey;
		private int isProduct = 1;
        public Builder partnerID(String partnerID) {
            this.partnerID = partnerID;
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
		return "ShunfengConfig [partnerID=" + partnerID + ", secretKey=" + secretKey + ", isProduct=" + isProduct + "]";
	}
	
	
	
	
}