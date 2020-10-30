package net.kdks.config;

public class ZhongtongConfig {
	
	private ZhongtongConfig(Builder builder) {
		companyId = builder.companyId;
		secretKey = builder.secretKey;
		isProduct = builder.isProduct;
    }
	
	private String companyId;
	
	private String secretKey;
	
	private int isProduct;
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
		private String companyId;
		private String secretKey;
		private int isProduct = 1;
        public Builder companyId(String companyId) {
            this.companyId = companyId;
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

        public ZhongtongConfig build() {
            return new ZhongtongConfig(this);
        }
    }

	@Override
	public String toString() {
		return "ShentongConfig [companyId=" + companyId + ", secretKey=" + secretKey + ", isProduct=" + isProduct + "]";
	}
	
}
