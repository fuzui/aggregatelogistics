package net.kdks.config;

/**
 * 顺丰快递配置.
 * 
 * <p>API配置,包含[partnerID,requestID,checkWord],isProduct标识测试与生产环境<br>
 * 配置获取:<em><a href="http://qiao.sf-express.com/">顺丰开放平台</a></em>
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShunfengConfig {
	
	private ShunfengConfig(Builder builder) {
		partnerID = builder.partnerID;
		requestID = builder.requestID;
		checkWord = builder.checkWord;
		isProduct = builder.isProduct;
    }
	
	private String partnerID;
	private String requestID;
	private String checkWord;
	
	private int isProduct;
	
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
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
	
	public ShunfengConfig(String partnerID, String requestID, String checkWord) {
		super();
		this.partnerID = partnerID;
		this.requestID = requestID;
		this.checkWord = checkWord;
	}
	
	public ShunfengConfig(String partnerID, String requestID, String checkWord, int isProduct) {
		super();
		this.partnerID = partnerID;
		this.requestID = requestID;
		this.checkWord = checkWord;
		this.isProduct = isProduct;
	}
	
	public static class Builder {
		private String partnerID;
		private String requestID;
		private String checkWord;
		private int isProduct = 1;
        public Builder partnerID(String partnerID) {
            this.partnerID = partnerID;
            return this;
        }

        public Builder requestID(String requestID) {
            this.requestID = requestID;
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
		return "ShunfengConfig [partnerID=" + partnerID + ", requestID=" + requestID + ", checkWord=" + checkWord
				+ ", isProduct=" + isProduct + "]";
	}
	
	
	
	
}
