package net.kdks.config;

/**
 * 顺丰快递配置.
 * 
 * <p>API配置,包含[partnerId,requestId,checkWord],isProduct标识测试与生产环境<br>
 * 配置获取:<em><a href="http://qiao.sf-express.com/">顺丰开放平台</a></em>
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShunfengConfig {
	
	private ShunfengConfig(Builder builder) {
		partnerId = builder.partnerId;
		requestId = builder.requestId;
		checkWord = builder.checkWord;
		isProduct = builder.isProduct;
    }
	
	private String partnerId;
	private String requestId;
	private String checkWord;
	
	private int isProduct;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCheckWord() {
		return checkWord;
	}
	public void setCheckWord(String checkWord) {
		this.checkWord = checkWord;
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
	
	public ShunfengConfig() {
		super();
	}
	public ShunfengConfig(String partnerId, String requestId, String checkWord) {
		super();
		this.partnerId = partnerId;
		this.requestId = requestId;
		this.checkWord = checkWord;
	}
	
	public ShunfengConfig(String partnerId, String requestId, String checkWord, int isProduct) {
		super();
		this.partnerId = partnerId;
		this.requestId = requestId;
		this.checkWord = checkWord;
		this.isProduct = isProduct;
	}
	
	public static class Builder {
		private String partnerId;
		private String requestId;
		private String checkWord;
		private int isProduct = 1;
        public Builder partnerId(String partnerId) {
            this.partnerId = partnerId;
            return this;
        }

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }
        
        public Builder checkWord(String checkWord) {
            this.checkWord = checkWord;
            return this;
        }
        
        public Builder isProduct(int isProduct) {
            this.isProduct = isProduct;
            return this;
        }

        public ShunfengConfig build() {
            return new ShunfengConfig(this);
        }
    }
	
	@Override
	public String toString() {
		return "ShunfengConfig [partnerId=" + partnerId + ", requestId=" + requestId + ", checkWord=" + checkWord
				+ ", isProduct=" + isProduct + "]";
	}
	
	
	
	
}
